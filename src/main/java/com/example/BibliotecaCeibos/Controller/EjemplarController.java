package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Ejemplar;
import com.example.BibliotecaCeibos.Repository.EjemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ejemplares")
@CrossOrigin("*")
public class EjemplarController {

    @Autowired
    private EjemplarRepository ejemplarRepository;

    @GetMapping
    public List<Ejemplar> getAll() {
        return ejemplarRepository.findAll();
    }

    @GetMapping("/{id}")
    public Ejemplar getById(@PathVariable Integer id) {
        return ejemplarRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Ejemplar create(@RequestBody Ejemplar ejemplar) {
        return ejemplarRepository.save(ejemplar);
    }

    @PutMapping("/{id}")
    public Ejemplar update(@PathVariable Integer id, @RequestBody Ejemplar ejemplarDetails) {
        Ejemplar ejemplar = ejemplarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con id: " + id));


        if (ejemplarDetails.getCodigoInterno() != null) {
            ejemplar.setCodigoInterno(ejemplarDetails.getCodigoInterno());
        }
        if (ejemplarDetails.getEstadoEjemplar() != null) {
            ejemplar.setEstadoEjemplar(ejemplarDetails.getEstadoEjemplar());
        }
        if (ejemplarDetails.getEstado() != null) {
            ejemplar.setEstado(ejemplarDetails.getEstado());
        }
        if (ejemplarDetails.getLibro() != null) {
            ejemplar.setLibro(ejemplarDetails.getLibro());
        }

        ejemplar.setIdEjemplar(id);
        return ejemplarRepository.save(ejemplar);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        ejemplarRepository.deleteById(id);
    }
}
