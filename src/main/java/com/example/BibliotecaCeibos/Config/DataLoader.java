package com.example.BibliotecaCeibos.Config;

import com.example.BibliotecaCeibos.Entity.Cliente;
import com.example.BibliotecaCeibos.Entity.Empleado;
import com.example.BibliotecaCeibos.Entity.RolCliente;
import com.example.BibliotecaCeibos.Entity.RolEmpleado;
import com.example.BibliotecaCeibos.Repository.ClienteRepository;
import com.example.BibliotecaCeibos.Repository.EmpleadoRepository;
import com.example.BibliotecaCeibos.Repository.RolClienteRepository;
import com.example.BibliotecaCeibos.Repository.RolEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private RolClienteRepository rolClienteRepository;
    @Autowired
    private RolEmpleadoRepository rolEmpleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        RolCliente rolUser;
        if (rolClienteRepository.count() == 0) {
            rolUser = new RolCliente();
            rolUser.setDescripcion("Usuario");
            rolClienteRepository.save(rolUser);
        } else {
            rolUser = rolClienteRepository.findAll().get(0);
        }

        RolEmpleado rolAdmin;
        if (rolEmpleadoRepository.count() == 0) {
            rolAdmin = new RolEmpleado();
            rolAdmin.setDescripcion("Administrador");
            rolEmpleadoRepository.save(rolAdmin);
        } else {
            rolAdmin = rolEmpleadoRepository.findAll().get(0);
        }


        List<Cliente> clientes = clienteRepository.findAll();
        boolean userExists = clientes.stream().anyMatch(c -> c.getEmail().equals("user"));

        if (!userExists) {
            Cliente cliente = new Cliente();
            cliente.setNombres("Usuario");
            cliente.setApellidoPaterno("Normal");
            cliente.setApellidoMaterno("Demo");
            cliente.setEmail("user@gmail.com");
            cliente.setContrasena(passwordEncoder.encode("user123"));
            cliente.setEstado("Activo");
            cliente.setFechaRegistro(new Date());
            cliente.setRolCliente(rolUser);
            clienteRepository.save(cliente);
            System.out.println(">>> Usuario 'user' creado con contraseña encriptada.");
        }

        List<Empleado> empleados = empleadoRepository.findAll();
        boolean adminExists = empleados.stream().anyMatch(e -> e.getEmail().equals("admin"));

        if (!adminExists) {
            Empleado admin = new Empleado();
            admin.setNombres("Admin");
            admin.setApellidoPaterno("Principal");
            admin.setApellidoMaterno("Demo");
            admin.setEmail("admin@gmail.com");
            admin.setContrasena(passwordEncoder.encode("admin123"));
            admin.setEstado("Activo");
            admin.setCargo("Bibliotecario");
            admin.setFechaRegistro(new Date());
            admin.setRolEmpleado(rolAdmin);
            empleadoRepository.save(admin);
            System.out.println(">>> Usuario 'admin' creado con contraseña encriptada.");
        }
    }
}