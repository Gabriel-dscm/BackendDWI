package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Autor;
import com.example.BibliotecaCeibos.Repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/autores")
@CrossOrigin("*")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public List<Autor> getAll() {
        return autorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Autor getById(@PathVariable Integer id) {
        return autorRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Autor create(@RequestBody Autor autor) {
        return autorRepository.save(autor);
    }

    @PutMapping("/{id}")
    public Autor update(@PathVariable Integer id, @RequestBody Autor autor) {
        autor.setIdAutor(id);
        return autorRepository.save(autor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        autorRepository.deleteById(id);
    }
}
