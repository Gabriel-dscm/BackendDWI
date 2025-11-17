package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Libro;
import com.example.BibliotecaCeibos.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin("*")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping
    public List<Libro> getAll() {
        return libroRepository.findAllWithDetails();
    }

    @GetMapping("/{id}")
    public Libro getById(@PathVariable Integer id) {
        return libroRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Libro create(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @PutMapping("/{id}")
    public Libro update(@PathVariable Integer id, @RequestBody Libro libro) {
        libro.setIdLibro(id);
        return libroRepository.save(libro);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        libroRepository.deleteById(id);
    }
}
