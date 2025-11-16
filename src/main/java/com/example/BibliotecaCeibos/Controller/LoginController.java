package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Cliente;
import com.example.BibliotecaCeibos.Entity.Empleado;
import com.example.BibliotecaCeibos.Repository.ClienteRepository;
import com.example.BibliotecaCeibos.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        Map<String, Object> response = new HashMap<>();

        List<Empleado> empleados = empleadoRepository.findAll();
        for (Empleado emp : empleados) {
            if (emp.getEmail().equals(username) && emp.getContrasena().equals(password)) {
                response.put("role", "admin");
                response.put("access", true);
                return response;
            }
        }

        List<Cliente> clientes = clienteRepository.findAll();
        for (Cliente cli : clientes) {
            if (cli.getEmail().equals(username) && cli.getContrasena().equals(password)) {
                response.put("role", "user");
                response.put("access", true);
                return response;
            }
        }

        response.put("access", false);
        return response;
    }
}