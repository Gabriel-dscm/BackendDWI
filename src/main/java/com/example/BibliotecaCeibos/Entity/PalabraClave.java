package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PalabraClave")
@Getter
@Setter
public class PalabraClave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPalabraClave")
    private Integer idPalabraClave;

    @Column(name = "Descripcion")
    private String descripcion;
}
