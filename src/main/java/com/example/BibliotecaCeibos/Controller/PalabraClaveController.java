package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.PalabraClave;
import com.example.BibliotecaCeibos.Repository.PalabraClaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/palabraclave")
@CrossOrigin("*")
public class PalabraClaveController {

    @Autowired
    private PalabraClaveRepository palabraClaveRepository;

    @GetMapping
    public List<PalabraClave> getAll() {
        return palabraClaveRepository.findAll();
    }

    @GetMapping("/{id}")
    public PalabraClave getById(@PathVariable Integer id) {
        return palabraClaveRepository.findById(id).orElse(null);
    }

    @PostMapping
    public PalabraClave create(@RequestBody PalabraClave palabraClave) {
        return palabraClaveRepository.save(palabraClave);
    }

    @PutMapping("/{id}")
    public PalabraClave update(@PathVariable Integer id, @RequestBody PalabraClave palabraClave) {
        palabraClave.setIdPalabraClave(id);
        return palabraClaveRepository.save(palabraClave);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        palabraClaveRepository.deleteById(id);
    }
}
