package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "Penalidad")
@Getter
@Setter
public class Penalidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPenalidad")
    private Integer idPenalidad;

    @Column(name = "Motivo")
    private String motivo;

    @Column(name = "Tipo")
    private String tipo;

    @Column(name = "Monto")
    private Double monto;

    @Column(name = "Estado")
    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaPenalidad")
    private Date fechaPenalidad;

    @ManyToOne
    @JoinColumn(name = "IdDevolucion")
    private Devolucion devolucion;

    @ManyToOne
    @JoinColumn(name = "IdCliente")
    private Cliente cliente;
}