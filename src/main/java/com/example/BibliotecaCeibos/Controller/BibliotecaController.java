package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Libro;
import com.example.BibliotecaCeibos.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/biblioteca")
@CrossOrigin("*")
public class BibliotecaController {

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping("/libros")
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @GetMapping("/libros/{id}")
    public Libro obtenerLibro(@PathVariable Integer id) {
        return libroRepository.findById(id).orElse(null);
    }
}
