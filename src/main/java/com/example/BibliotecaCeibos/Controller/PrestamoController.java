package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Prestamo;
import com.example.BibliotecaCeibos.Repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin("*")
public class PrestamoController {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @GetMapping
    public List<Prestamo> getAll() {
        return prestamoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Prestamo getById(@PathVariable Integer id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Prestamo create(@RequestBody Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @PutMapping("/{id}")
    public Prestamo update(@PathVariable Integer id, @RequestBody Prestamo prestamo) {
        prestamo.setIdPrestamo(id);
        return prestamoRepository.save(prestamo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        prestamoRepository.deleteById(id);
    }
}
