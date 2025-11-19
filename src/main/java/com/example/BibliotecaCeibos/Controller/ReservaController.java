package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Cliente;
import com.example.BibliotecaCeibos.Entity.Ejemplar;
import com.example.BibliotecaCeibos.Entity.Libro;
import com.example.BibliotecaCeibos.Entity.Reserva;
import com.example.BibliotecaCeibos.Repository.ClienteRepository;
import com.example.BibliotecaCeibos.Repository.EjemplarRepository;
import com.example.BibliotecaCeibos.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin("*")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EjemplarRepository ejemplarRepository;

    @GetMapping
    public List<Reserva> getAll(
            @RequestParam(required = false) String clienteNombre,
            @RequestParam(required = false) String ejemplarInfo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaReserva,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaExpiracion
    ) {
        if (clienteNombre == null && ejemplarInfo == null && fechaReserva == null && fechaExpiracion == null) {
            return reservaRepository.findAllWithDetails();
        }
        return reservaRepository.searchReservas(clienteNombre, ejemplarInfo, fechaReserva, fechaExpiracion);
    }

    @GetMapping("/reporte-completadas")
    public List<Reserva> getReporteCompletadas(
            @RequestParam(required = false) String clienteNombre,
            @RequestParam(required = false) String ejemplarInfo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaReserva,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaExpiracion
    ) {
        return reservaRepository.searchReservasCompletadas(clienteNombre, ejemplarInfo, fechaReserva, fechaExpiracion);
    }

    @GetMapping("/mis-reservas")
    public List<Reserva> getMisReservas() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = clienteRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        return reservaRepository.findReservasActivasByCliente(cliente);
    }

    @GetMapping("/{id}")
    public Reserva getById(@PathVariable Integer id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Reserva create(@RequestBody Reserva reservaRequest) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        Cliente cliente = clienteRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        if (reservaRequest.getEjemplar() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos de ejemplar inválidos");
        }

        Ejemplar ejemplarSeleccionado;

        if (reservaRequest.getEjemplar().getIdEjemplar() != null) {
            ejemplarSeleccionado = ejemplarRepository.findById(reservaRequest.getEjemplar().getIdEjemplar())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ejemplar no encontrado"));

            if (!"Disponible".equals(ejemplarSeleccionado.getEstado())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El ejemplar seleccionado ya no está disponible");
            }
        }
        else if (reservaRequest.getEjemplar().getLibro() != null) {
            Libro libro = reservaRequest.getEjemplar().getLibro();
            ejemplarSeleccionado = ejemplarRepository.findFirstByLibroAndEstado(libro, "Disponible")
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay ejemplares disponibles de este libro"));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe especificar un Ejemplar ID o un Libro");
        }

        ejemplarSeleccionado.setEstado("Reservado");
        ejemplarRepository.save(ejemplarSeleccionado);

        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setCliente(cliente);
        nuevaReserva.setEjemplar(ejemplarSeleccionado);
        nuevaReserva.setFechaReserva(new Date());
        nuevaReserva.setFechaExpiracion(reservaRequest.getFechaExpiracion());

        return reservaRepository.save(nuevaReserva);
    }

    @PutMapping("/{id}")
    public Reserva update(@PathVariable Integer id, @RequestBody Reserva reserva) {
        reserva.setIdReserva(id);
        return reservaRepository.save(reserva);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        if (reserva != null && reserva.getEjemplar() != null) {
            Ejemplar ejemplar = reserva.getEjemplar();
            ejemplar.setEstado("Disponible");
            ejemplarRepository.save(ejemplar);
        }
        reservaRepository.deleteById(id);
    }
}