package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.SolicitudLibro;
import com.example.BibliotecaCeibos.Repository.SolicitudLibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin("*")
public class SolicitudLibroController {

    @Autowired
    private SolicitudLibroRepository solicitudLibroRepository;

    @GetMapping
    public List<SolicitudLibro> getAll() {
        return solicitudLibroRepository.findAll();
    }

    @GetMapping("/{id}")
    public SolicitudLibro getById(@PathVariable Integer id) {
        return solicitudLibroRepository.findById(id).orElse(null);
    }

    @PostMapping
    public SolicitudLibro create(@RequestBody SolicitudLibro solicitudLibro) {
        return solicitudLibroRepository.save(solicitudLibro);
    }

    @PutMapping("/{id}")
    public SolicitudLibro update(@PathVariable Integer id, @RequestBody SolicitudLibro solicitudLibro) {
        solicitudLibro.setIdSolicitud(id);
        return solicitudLibroRepository.save(solicitudLibro);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        solicitudLibroRepository.deleteById(id);
    }
}
