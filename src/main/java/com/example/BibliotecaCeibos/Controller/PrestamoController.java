package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.*;
import com.example.BibliotecaCeibos.Repository.ClienteRepository;
import com.example.BibliotecaCeibos.Repository.EjemplarRepository;
import com.example.BibliotecaCeibos.Repository.PrestamoRepository;
import com.example.BibliotecaCeibos.Repository.ReservaRepository;
import com.example.BibliotecaCeibos.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin("*")
public class PrestamoController {

    @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EjemplarRepository ejemplarRepository;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public List<Prestamo> getAll(
            @RequestParam(required = false) String clienteNombre,
            @RequestParam(required = false) String ejemplarInfo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaPrestamo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDevolucion
    ) {
        if (clienteNombre == null && ejemplarInfo == null && fechaPrestamo == null && fechaDevolucion == null) {
            return prestamoRepository.findAllWithDetails("ACTIVO");
        }
        return prestamoRepository.searchPrestamos(clienteNombre, ejemplarInfo, fechaPrestamo, fechaDevolucion, "ACTIVO");
    }

    @GetMapping("/activos-por-cliente/{clienteId}")
    public List<Prestamo> getActivosPorCliente(@PathVariable Integer clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        return prestamoRepository.findListByClienteAndEstado(cliente, "ACTIVO");
    }

    @GetMapping("/mis-prestamos")
    public List<Prestamo> getMisPrestamos() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = clienteRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        return prestamoRepository.findListByClienteAndEstado(cliente, "ACTIVO");
    }

    @GetMapping("/{id}")
    public Prestamo getById(@PathVariable Integer id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    @PostMapping
    @Transactional
    public Prestamo create(@RequestBody Prestamo prestamoRequest) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = clienteRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        if (prestamoRequest.getEjemplar() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos de ejemplar inválidos");
        }

        Ejemplar ejemplarSeleccionado;

        if (prestamoRequest.getEjemplar().getIdEjemplar() != null) {
            ejemplarSeleccionado = ejemplarRepository.findById(prestamoRequest.getEjemplar().getIdEjemplar())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ejemplar no encontrado"));
            if (!"Disponible".equals(ejemplarSeleccionado.getEstado())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El ejemplar seleccionado ya no está disponible");
            }
        }
        else if (prestamoRequest.getEjemplar().getLibro() != null) {
            Libro libro = prestamoRequest.getEjemplar().getLibro();
            ejemplarSeleccionado = ejemplarRepository.findFirstByLibroAndEstado(libro, "Disponible")
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay ejemplares disponibles de este libro"));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe especificar un Ejemplar ID o un Libro");
        }

        ejemplarSeleccionado.setEstado("Prestado");
        ejemplarRepository.save(ejemplarSeleccionado);

        Prestamo nuevoPrestamo = new Prestamo();
        nuevoPrestamo.setEstado("ACTIVO");
        nuevoPrestamo.setCliente(cliente);
        nuevoPrestamo.setEjemplar(ejemplarSeleccionado);
        nuevoPrestamo.setFechaPrestamo(prestamoRequest.getFechaPrestamo());
        nuevoPrestamo.setFechaDevolucion(prestamoRequest.getFechaDevolucion());

        return prestamoRepository.save(nuevoPrestamo);
    }

    @PostMapping("/convert-from-reserva")
    @Transactional
    public Prestamo convertFromReserva(
            @RequestBody Prestamo prestamoRequest,
            @RequestParam Integer reservaId
    ) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada"));

        Cliente cliente = reserva.getCliente();
        Ejemplar ejemplar = reserva.getEjemplar();

        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado admin = empleadoRepository.findByEmail(adminEmail)
                .orElse(null);

        if (cliente == null || ejemplar == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La reserva está incompleta");
        }

        ejemplar.setEstado("Prestado");
        ejemplarRepository.save(ejemplar);

        Prestamo nuevoPrestamo = new Prestamo();
        nuevoPrestamo.setCliente(cliente);
        nuevoPrestamo.setEstado("ACTIVO");
        nuevoPrestamo.setEjemplar(ejemplar);
        nuevoPrestamo.setReserva(reserva);
        nuevoPrestamo.setFechaPrestamo(prestamoRequest.getFechaPrestamo());
        nuevoPrestamo.setFechaDevolucion(prestamoRequest.getFechaDevolucion());

        nuevoPrestamo.setEmpleado(admin);
        nuevoPrestamo.setEstadoLibro("Prestado");
        Prestamo prestamoGuardado = prestamoRepository.save(nuevoPrestamo);

        reserva.setEstado("COMPLETADA");
        reservaRepository.save(reserva);

        return prestamoGuardado;
    }

    @PutMapping("/{id}")
    public Prestamo update(@PathVariable Integer id, @RequestBody Prestamo prestamo) {
        prestamo.setIdPrestamo(id);
        return prestamoRepository.save(prestamo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        prestamoRepository.deleteById(id);
    }
}