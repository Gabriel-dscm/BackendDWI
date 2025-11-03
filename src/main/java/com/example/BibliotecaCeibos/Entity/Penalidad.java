package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Penalidad")
@Getter
@Setter
public class Penalidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPenalidad")
    private Integer idPenalidad;

    @Column(name = "TipoPenalidad")
    private String tipoPenalidad;

    @Column(name = "Monto")
    private Double monto;

    @Column(name = "EstadoPenalidad")
    private String estadoPenalidad;

    @ManyToOne
    @JoinColumn(name = "IdDevolucion")
    private Devolucion devolucion;
}
