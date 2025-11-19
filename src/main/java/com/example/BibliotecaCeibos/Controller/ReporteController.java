package com.example.BibliotecaCeibos.Controller;

import com.example.BibliotecaCeibos.Entity.Prestamo;
import com.example.BibliotecaCeibos.Entity.ReporteDTO;
import com.example.BibliotecaCeibos.Entity.Reserva;
import com.example.BibliotecaCeibos.Repository.PrestamoRepository;
import com.example.BibliotecaCeibos.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin("*")
public class ReporteController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    @GetMapping
    public List<ReporteDTO> getReportesUnificados(
            @RequestParam(required = false) String clienteNombre,
            @RequestParam(required = false) String ejemplarInfo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio
    ) {
        List<ReporteDTO> reportes = new ArrayList<>();

        List<Reserva> reservas = reservaRepository.searchReservasCompletadas(clienteNombre, ejemplarInfo, fechaInicio, null);
        for (Reserva r : reservas) {
            reportes.add(new ReporteDTO(
                    "Reserva",
                    r.getFechaReserva(),
                    r.getFechaExpiracion(),
                    r.getCliente() != null ? (r.getCliente().getNombres() + " " + r.getCliente().getApellidoPaterno()) : "N/A",
                    r.getEjemplar() != null && r.getEjemplar().getLibro() != null ? r.getEjemplar().getLibro().getTitulo() : "N/A",
                    r.getEstado()
            ));
        }

        List<Prestamo> prestamos = prestamoRepository.searchPrestamosDevueltos(clienteNombre, ejemplarInfo, fechaInicio);
        for (Prestamo p : prestamos) {
            reportes.add(new ReporteDTO(
                    "Préstamo",
                    p.getFechaPrestamo(),
                    p.getFechaDevolucion(),
                    p.getCliente() != null ? (p.getCliente().getNombres() + " " + p.getCliente().getApellidoPaterno()) : "N/A",
                    p.getEjemplar() != null && p.getEjemplar().getLibro() != null ? p.getEjemplar().getLibro().getTitulo() : "N/A",
                    p.getEstado()
            ));
        }

        reportes.sort(Comparator.comparing(ReporteDTO::getFechaInicio).reversed());

        return reportes;
    }
}