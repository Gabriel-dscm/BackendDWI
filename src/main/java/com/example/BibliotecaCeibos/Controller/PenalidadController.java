package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Penalidad;
import com.example.BibliotecaCeibos.Repository.PenalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/penalidades")
@CrossOrigin("*")
public class PenalidadController {

    @Autowired
    private PenalidadRepository penalidadRepository;

    @GetMapping
    public List<Penalidad> getAll(
            @RequestParam(required = false) String clienteNombre,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha
    ) {
        if (clienteNombre == null && estado == null && fecha == null) {
            return penalidadRepository.findAll();
        }
        return penalidadRepository.searchPenalidades(clienteNombre, estado, fecha);
    }

    @GetMapping("/{id}")
    public Penalidad getById(@PathVariable Integer id) {
        return penalidadRepository.findById(id).orElse(null);
    }

    @PutMapping("/{idPenalidad}/pagar")
    public ResponseEntity<?> pagarPenalidad(@PathVariable Integer idPenalidad) {
        Optional<Penalidad> penalidadOpt = penalidadRepository.findById(idPenalidad);

        if (penalidadOpt.isPresent()) {
            Penalidad penalidad = penalidadOpt.get();
            penalidad.setEstado("Pagada");
            penalidadRepository.save(penalidad);
            return ResponseEntity.ok(penalidad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}