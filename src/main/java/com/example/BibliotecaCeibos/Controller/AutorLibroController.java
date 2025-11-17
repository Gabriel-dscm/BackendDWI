package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.AutorLibro;
import com.example.BibliotecaCeibos.Repository.AutorLibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/autorlibro")
@CrossOrigin("*")
public class AutorLibroController {

    @Autowired
    private AutorLibroRepository autorLibroRepository;

    @GetMapping
    public List<AutorLibro> getAll() {
        return autorLibroRepository.findAll();
    }


    @GetMapping("/{id}")
    public AutorLibro getById(@PathVariable Integer id) {
        return autorLibroRepository.findById(id).orElse(null);
    }

    @PostMapping
    public AutorLibro create(@RequestBody AutorLibro autorLibro) {
        autorLibro.setIdAutorLibro(null);
        return autorLibroRepository.save(autorLibro);
    }

    @PutMapping("/{id}")
    public AutorLibro update(@PathVariable Integer id, @RequestBody AutorLibro autorLibroDetails) {
        AutorLibro autorLibro = autorLibroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relación no encontrada con id: " + id));

        autorLibro.setAutor(autorLibroDetails.getAutor());
        autorLibro.setLibro(autorLibroDetails.getLibro());

        autorLibro.setIdAutorLibro(id);

        return autorLibroRepository.save(autorLibro);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        autorLibroRepository.deleteById(id);
    }
}