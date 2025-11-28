package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Penalidad;
import com.example.BibliotecaCeibos.Repository.PenalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

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

    @PutMapping("/{id}/pagar")
    public Penalidad resolverPenalidad(@PathVariable Integer id) {
        Penalidad penalidad = penalidadRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Penalidad no encontrada"));
        if ("Monetaria".equalsIgnoreCase(penalidad.getTipo())) {
            penalidad.setEstado("Pagada");
        } else {
            penalidad.setEstado("Concluida");
        }

        return penalidadRepository.save(penalidad);
    }
}