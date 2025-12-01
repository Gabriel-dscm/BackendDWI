package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Ejemplar;
import com.example.BibliotecaCeibos.Entity.Libro; // <-- 1. IMPORTA LIBRO
import com.example.BibliotecaCeibos.Repository.EjemplarRepository;
import com.example.BibliotecaCeibos.Repository.LibroRepository; // <-- 2. IMPORTA LIBRO REPO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map; // <-- 3. IMPORTA MAP

@RestController
@RequestMapping("/api/ejemplares")
@CrossOrigin("*")
public class EjemplarController {

    @Autowired
    private EjemplarRepository ejemplarRepository;

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping
    public List<Ejemplar> getAll() {
        return ejemplarRepository.findAllWithDetails();
    }

    @GetMapping("/{id}")
    public Ejemplar getById(@PathVariable Integer id) {
        return ejemplarRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Ejemplar create(@RequestBody Ejemplar ejemplar) {
        ejemplar.setIdEjemplar(null);
        return ejemplarRepository.save(ejemplar);
    }

    @PutMapping("/{id}")
    public Ejemplar update(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {

        Ejemplar ejemplar = ejemplarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con id: " + id));

        if (updates.containsKey("codigoInterno")) {
            ejemplar.setCodigoInterno((String) updates.get("codigoInterno"));
        }
        if (updates.containsKey("estadoEjemplar")) {
            ejemplar.setEstadoEjemplar((String) updates.get("estadoEjemplar"));
        }
        if (updates.containsKey("estado")) {
            // Esto arregla la función "Dar de Baja"
            ejemplar.setEstado((String) updates.get("estado"));
        }
        if (updates.containsKey("libro")) {

            try {
                Map<String, Object> libroMap = (Map<String, Object>) updates.get("libro");
                Integer libroId = (Integer) libroMap.get("idLibro");
                Libro libro = libroRepository.findById(libroId)
                        .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + libroId));
                ejemplar.setLibro(libro);
            } catch (Exception e) {
                throw new RuntimeException("Error al procesar el objeto 'libro': " + e.getMessage());
            }
        }

        return ejemplarRepository.save(ejemplar);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        ejemplarRepository.deleteById(id);
    }
}
