package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Reserva;
import com.example.BibliotecaCeibos.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin("*")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    public List<Reserva> getAll() {
        return reservaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reserva getById(@PathVariable Integer id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Reserva create(@RequestBody Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @PutMapping("/{id}")
    public Reserva update(@PathVariable Integer id, @RequestBody Reserva reserva) {
        reserva.setIdReserva(id);
        return reservaRepository.save(reserva);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        reservaRepository.deleteById(id);
    }
}
