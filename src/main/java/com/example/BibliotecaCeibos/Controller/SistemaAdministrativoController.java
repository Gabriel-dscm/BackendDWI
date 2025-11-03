package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Libro;
import com.example.BibliotecaCeibos.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class SistemaAdministrativoController {

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping("/libros")
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    @PostMapping("/libros")
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @PutMapping("/libros/{id}")
    public Libro actualizarLibro(@PathVariable Integer id, @RequestBody Libro libro) {
        libro.setIdLibro(id);
        return libroRepository.save(libro);
    }

    @DeleteMapping("/libros/{id}")
    public void eliminarLibro(@PathVariable Integer id) {
        libroRepository.deleteById(id);
    }
}
