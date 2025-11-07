package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Penalidad;
import com.example.BibliotecaCeibos.Repository.PenalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/penalidades")
@CrossOrigin("*")
public class PenalidadController {

    @Autowired
    private PenalidadRepository penalidadRepository;

    @GetMapping
    public List<Penalidad> getAll() {
        return penalidadRepository.findAll();
    }

    @GetMapping("/{id}")
    public Penalidad getById(@PathVariable Integer id) {
        return penalidadRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Penalidad create(@RequestBody Penalidad penalidad) {
        return penalidadRepository.save(penalidad);
    }

    @PutMapping("/{id}")
    public Penalidad update(@PathVariable Integer id, @RequestBody Penalidad penalidad) {
        penalidad.setIdPenalidad(id);
        return penalidadRepository.save(penalidad);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        penalidadRepository.deleteById(id);
    }
}
