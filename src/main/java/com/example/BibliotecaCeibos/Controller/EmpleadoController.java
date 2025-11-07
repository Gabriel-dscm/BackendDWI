package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Empleado;
import com.example.BibliotecaCeibos.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin("*")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public List<Empleado> getAll() {
        return empleadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Empleado getById(@PathVariable Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Empleado create(@RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/{id}")
    public Empleado update(@PathVariable Integer id, @RequestBody Empleado empleado) {
        empleado.setIdEmpleado(id);
        return empleadoRepository.save(empleado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        empleadoRepository.deleteById(id);
    }
}
