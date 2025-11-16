package com.example.BibliotecaCeibos.Service;

import com.example.BibliotecaCeibos.Entity.Cliente;
import com.example.BibliotecaCeibos.Entity.Empleado;
import com.example.BibliotecaCeibos.Repository.ClienteRepository;
import com.example.BibliotecaCeibos.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Empleado> empleadoOpt = empleadoRepository.findAll().stream()
                .filter(e -> e.getEmail().equals(username))
                .findFirst();

        if (empleadoOpt.isPresent()) {
            Empleado empleado = empleadoOpt.get();
            Collection<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new User(empleado.getEmail(), empleado.getContrasena(), authorities);
        }

        Optional<Cliente> clienteOpt = clienteRepository.findAll().stream()
                .filter(c -> c.getEmail().equals(username))
                .findFirst();

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            Collection<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new User(cliente.getEmail(), cliente.getContrasena(), authorities);
        }

        throw new UsernameNotFoundException("Usuario no encontrado con email: " + username);
    }
}