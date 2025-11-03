package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "Devolucion")
@Getter
@Setter
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDevolucion")
    private Integer idDevolucion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaDevolucion")
    private Date fechaDevolucion;

    @Column(name = "EstadoLibro")
    private String estadoLibro;

    @Column(name = "Observaciones")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "IdPrestamo")
    private Prestamo prestamo;

    @OneToMany(mappedBy = "devolucion", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Penalidad> penalidades;
}
