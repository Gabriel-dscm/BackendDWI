package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Devolucion;
import com.example.BibliotecaCeibos.Repository.DevolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devoluciones")
@CrossOrigin("*")
public class DevolucionController {

    @Autowired
    private DevolucionRepository devolucionRepository;

    @GetMapping
    public List<Devolucion> getAll() {
        return devolucionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Devolucion getById(@PathVariable Integer id) {
        return devolucionRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Devolucion create(@RequestBody Devolucion devolucion) {
        return devolucionRepository.save(devolucion);
    }

    @PutMapping("/{id}")
    public Devolucion update(@PathVariable Integer id, @RequestBody Devolucion devolucion) {
        devolucion.setIdDevolucion(id);
        return devolucionRepository.save(devolucion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        devolucionRepository.deleteById(id);
    }
}
