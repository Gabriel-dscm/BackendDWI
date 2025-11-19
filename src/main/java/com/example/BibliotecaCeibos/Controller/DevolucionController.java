package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.*;
import com.example.BibliotecaCeibos.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/devoluciones")
@CrossOrigin("*")
public class DevolucionController {

    @Autowired
    private DevolucionRepository devolucionRepository;
    @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    private EjemplarRepository ejemplarRepository;
    @Autowired
    private PenalidadRepository penalidadRepository;

    @GetMapping
    public List<Devolucion> getAll() {
        return devolucionRepository.findAll();
    }

    @PostMapping
    @Transactional
    public Devolucion registrarDevolucion(
            @RequestBody Devolucion devolucionRequest,
            @RequestParam Integer prestamoId
    ) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Préstamo no encontrado"));

        Ejemplar ejemplar = prestamo.getEjemplar();
        if (ejemplar == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Préstamo sin ejemplar");
        }

        prestamo.setEstado("DEVUELTO");
        prestamoRepository.save(prestamo);

        ejemplar.setEstado("Disponible");
        ejemplarRepository.save(ejemplar);

        Penalidad penalidadTransient = devolucionRequest.getPenalidad();

        devolucionRequest.setPenalidad(null);
        devolucionRequest.setFechaDevolucion(new Date());
        devolucionRequest.setPrestamo(prestamo);

        Devolucion nuevaDevolucion = devolucionRepository.save(devolucionRequest);

        if (penalidadTransient != null) {
            Penalidad penalidad = penalidadTransient;
            penalidad.setDevolucion(nuevaDevolucion);
            penalidad.setCliente(prestamo.getCliente());
            penalidad.setFechaPenalidad(new Date());
            penalidad.setEstado("Pendiente");
            penalidadRepository.save(penalidad);
        }

        return nuevaDevolucion;
    }
}