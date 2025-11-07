package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.LibroPalabraClave;
import com.example.BibliotecaCeibos.Entity.LibroPalabraClaveId;
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

    @GetMapping("/{idLibro}/{idPalabraClave}")
    public LibroPalabraClave getById(@PathVariable Integer idLibro, @PathVariable Integer idPalabraClave) {
        LibroPalabraClaveId id = new LibroPalabraClaveId();
        id.setIdLibro(idLibro);
        id.setIdPalabraClave(idPalabraClave);
        return libroPalabraClaveRepository.findById(id).orElse(null);
    }

    @PostMapping
    public LibroPalabraClave create(@RequestBody LibroPalabraClave libroPalabraClave) {
        return libroPalabraClaveRepository.save(libroPalabraClave);
    }

    @PutMapping("/{idLibro}/{idPalabraClave}")
    public LibroPalabraClave update(@PathVariable Integer idLibro, @PathVariable Integer idPalabraClave, @RequestBody LibroPalabraClave libroPalabraClave) {
        LibroPalabraClaveId id = new LibroPalabraClaveId();
        id.setIdLibro(idLibro);
        id.setIdPalabraClave(idPalabraClave);
        libroPalabraClave.setId(id);
        return libroPalabraClaveRepository.save(libroPalabraClave);
    }

    @DeleteMapping("/{idLibro}/{idPalabraClave}")
    public void delete(@PathVariable Integer idLibro, @PathVariable Integer idPalabraClave) {
        LibroPalabraClaveId id = new LibroPalabraClaveId();
        id.setIdLibro(idLibro);
        id.setIdPalabraClave(idPalabraClave);
        libroPalabraClaveRepository.deleteById(id);
    }
}
