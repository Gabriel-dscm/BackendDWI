package com.example.BibliotecaCeibos.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name = "id_ejemplar")
    private Integer idEjemplar;

    @Column(name = "codigo_interno")
    private String codigoInterno;

    @Column(name = "estado_ejemplar")
    private String estadoEjemplar;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_libro")
    @JsonIgnoreProperties("ejemplares")
    private Libro libro;
}