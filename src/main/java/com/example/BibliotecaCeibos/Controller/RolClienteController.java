package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.RolCliente;
import com.example.BibliotecaCeibos.Repository.RolClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rolesclientes")
@CrossOrigin("*")
public class RolClienteController {

    @Autowired
    private RolClienteRepository rolClienteRepository;

    @GetMapping
    public List<RolCliente> getAll() {
        return rolClienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public RolCliente getById(@PathVariable Integer id) {
        return rolClienteRepository.findById(id).orElse(null);
    }

    @PostMapping
    public RolCliente create(@RequestBody RolCliente rolCliente) {
        return rolClienteRepository.save(rolCliente);
    }

    @PutMapping("/{id}")
    public RolCliente update(@PathVariable Integer id, @RequestBody RolCliente rolCliente) {
        rolCliente.setIdRolCliente(id);
        return rolClienteRepository.save(rolCliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        rolClienteRepository.deleteById(id);
    }
}
