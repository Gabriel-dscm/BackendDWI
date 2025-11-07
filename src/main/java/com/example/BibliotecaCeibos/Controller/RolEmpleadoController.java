package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.RolEmpleado;
import com.example.BibliotecaCeibos.Repository.RolEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rolesempleados")
@CrossOrigin("*")
public class RolEmpleadoController {

    @Autowired
    private RolEmpleadoRepository rolEmpleadoRepository;

    @GetMapping
    public List<RolEmpleado> getAll() {
        return rolEmpleadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public RolEmpleado getById(@PathVariable Integer id) {
        return rolEmpleadoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public RolEmpleado create(@RequestBody RolEmpleado rolEmpleado) {
        return rolEmpleadoRepository.save(rolEmpleado);
    }

    @PutMapping("/{id}")
    public RolEmpleado update(@PathVariable Integer id, @RequestBody RolEmpleado rolEmpleado) {
        rolEmpleado.setIdRolEmpleado(id);
        return rolEmpleadoRepository.save(rolEmpleado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        rolEmpleadoRepository.deleteById(id);
    }
}
