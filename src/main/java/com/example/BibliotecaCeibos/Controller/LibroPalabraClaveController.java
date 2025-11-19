package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.LibroPalabraClave;
import com.example.BibliotecaCeibos.Repository.LibroPalabraClaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/libropalabraclave")
@CrossOrigin("*")
public class LibroPalabraClaveController {

    @Autowired
    private LibroPalabraClaveRepository libroPalabraClaveRepository;

    @GetMapping
    public List<LibroPalabraClave> getAll() {
        return libroPalabraClaveRepository.findAll();
    }

    @GetMapping("/{id}")
    public LibroPalabraClave getById(@PathVariable Integer id) {
        return libroPalabraClaveRepository.findById(id).orElse(null);
    }

    @PostMapping
    public LibroPalabraClave create(@RequestBody LibroPalabraClave libroPalabraClave) {
        libroPalabraClave.setIdLibroPalabraClave(null);
        return libroPalabraClaveRepository.save(libroPalabraClave);
    }

    @PutMapping("/{id}")
    public LibroPalabraClave update(@PathVariable Integer id, @RequestBody LibroPalabraClave details) {
        LibroPalabraClave lpc = libroPalabraClaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relación no encontrada"));

        lpc.setLibro(details.getLibro());
        lpc.setPalabraClave(details.getPalabraClave());
        lpc.setIdLibroPalabraClave(id);
        return libroPalabraClaveRepository.save(lpc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        libroPalabraClaveRepository.deleteById(id);
    }
}