package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Dewey;
import com.example.BibliotecaCeibos.Repository.DeweyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/dewey")
@CrossOrigin("*")
public class DeweyController {

    @Autowired
    private DeweyRepository deweyRepository;

    @GetMapping
    public List<Dewey> getAll() {
        return deweyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Dewey getById(@PathVariable Integer id) {
        return deweyRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Dewey create(@RequestBody Dewey dewey) {
        return deweyRepository.save(dewey);
    }

    @PutMapping("/{id}")
    public Dewey update(@PathVariable Integer id, @RequestBody Dewey dewey) {
        dewey.setIdDewey(id);
        return deweyRepository.save(dewey);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        deweyRepository.deleteById(id);
    }
}
