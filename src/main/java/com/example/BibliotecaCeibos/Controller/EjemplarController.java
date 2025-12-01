package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Ejemplar;
import com.example.BibliotecaCeibos.Entity.Libro;
import com.example.BibliotecaCeibos.Repository.EjemplarRepository;
import com.example.BibliotecaCeibos.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

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
        // Usa la versión optimizada del Repo
        return ejemplarRepository.findAllWithDetails();
    }

    @GetMapping("/{id}")
    public Ejemplar getById(@PathVariable Integer id) {
        return ejemplarRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Ejemplar create(@RequestBody Ejemplar ejemplar) {
        ejemplar.setIdEjemplar(null);
        // Al crear, asegurate de que el objeto Libro que viene solo tenga el ID para que no intente guardar cosas raras
        if(ejemplar.getLibro() != null && ejemplar.getLibro().getIdLibro() != null){
             // Opcional: Validar que el libro exista si quieres ser estricto, 
             // pero Hibernate lo maneja si solo mandas el ID.
        }
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
            ejemplar.setEstado((String) updates.get("estado"));
        }
        
        // MEJORA: Solo buscamos el libro si realmente enviaron un cambio de libro
        if (updates.containsKey("libro")) {
            try {
                Map<String, Object> libroMap = (Map<String, Object>) updates.get("libro");
                Integer nuevoLibroId = (Integer) libroMap.get("idLibro");

                // Solo hacemos la consulta si el ID es diferente al que ya tiene
                if (ejemplar.getLibro() == null || !ejemplar.getLibro().getIdLibro().equals(nuevoLibroId)) {
                    Libro libro = libroRepository.findById(nuevoLibroId)
                            .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + nuevoLibroId));
                    ejemplar.setLibro(libro);
                }
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