package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Config.JwtUtil;
import com.example.BibliotecaCeibos.Entity.Cliente;
import com.example.BibliotecaCeibos.Entity.Empleado;
import com.example.BibliotecaCeibos.Repository.ClienteRepository;
import com.example.BibliotecaCeibos.Repository.EmpleadoRepository;
import com.example.BibliotecaCeibos.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional; // Importante
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @PostMapping("/login")
    public Map<String, Object> createAuthenticationToken(@RequestBody Map<String, String> body) throws Exception {
        String username = body.get("username");
        String password = body.get("password");

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Email o contraseña incorrectos", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String nombreCompleto = "";

        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(username);
        if (clienteOpt.isPresent()) {
            Cliente c = clienteOpt.get();
            nombreCompleto = c.getNombres() + " " + c.getApellidoPaterno() + " " + c.getApellidoMaterno();
        } else {
            Optional<Empleado> empleadoOpt = empleadoRepository.findByEmail(username);
            if (empleadoOpt.isPresent()) {
                Empleado e = empleadoOpt.get();
                nombreCompleto = e.getNombres() + " " + e.getApellidoPaterno() + " " + e.getApellidoMaterno();
            }
        }

        final String jwt = jwtUtil.generateToken(userDetails, nombreCompleto);

        String role = userDetails.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toList()).contains("ROLE_ADMIN") ? "admin" : "user";

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("role", role);
        response.put("access", true);

        return response;
    }
}