package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Ejemplar")
@Getter
@Setter
public class Ejemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEjemplar")
    private Integer idEjemplar;

    @Column(name = "CodigoInterno")
    private String codigoInterno;

    @Column(name = "EstadoEjemplar")
    private String estadoEjemplar;

    @Column(name = "Estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "IdLibro")
    private Libro libro;
}
