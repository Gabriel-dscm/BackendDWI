package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.AutorLibro;
import com.example.BibliotecaCeibos.Entity.AutorLibroId;
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

    @GetMapping("/{idAutor}/{idLibro}")
    public AutorLibro getById(@PathVariable Integer idAutor, @PathVariable Integer idLibro) {
        AutorLibroId id = new AutorLibroId();
        id.setIdAutor(idAutor);
        id.setIdLibro(idLibro);
        return autorLibroRepository.findById(id).orElse(null);
    }

    @PostMapping
    public AutorLibro create(@RequestBody AutorLibro autorLibro) {
        return autorLibroRepository.save(autorLibro);
    }

    @PutMapping("/{idAutor}/{idLibro}")
    public AutorLibro update(@PathVariable Integer idAutor, @PathVariable Integer idLibro, @RequestBody AutorLibro autorLibro) {
        AutorLibroId id = new AutorLibroId();
        id.setIdAutor(idAutor);
        id.setIdLibro(idLibro);
        autorLibro.setId(id);
        return autorLibroRepository.save(autorLibro);
    }

    @DeleteMapping("/{idAutor}/{idLibro}")
    public void delete(@PathVariable Integer idAutor, @PathVariable Integer idLibro) {
        AutorLibroId id = new AutorLibroId();
        id.setIdAutor(idAutor);
        id.setIdLibro(idLibro);
        autorLibroRepository.deleteById(id);
    }
}
