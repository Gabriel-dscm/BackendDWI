package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "Prestamo")
@Getter
@Setter
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPrestamo")
    private Integer idPrestamo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaPrestamo")
    private Date fechaPrestamo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaDevolucion")
    private Date fechaDevolucion;

    @Column(name = "EstadoLibro")
    private String estadoLibro;

    @Column(name = "Estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "IdReserva")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "IdEjemplar")
    private Ejemplar ejemplar;

    @ManyToOne
    @JoinColumn(name = "IdCliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "IdEmpleado")
    private Empleado empleado;
}