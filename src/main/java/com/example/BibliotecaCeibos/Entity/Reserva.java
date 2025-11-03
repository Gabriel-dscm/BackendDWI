package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "Reserva")
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdReserva")
    private Integer idReserva;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaReserva")
    private Date fechaReserva;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaExpiracion")
    private Date fechaExpiracion;

    @ManyToOne
    @JoinColumn(name = "IdCliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "IdEjemplar")
    private Ejemplar ejemplar;
}
