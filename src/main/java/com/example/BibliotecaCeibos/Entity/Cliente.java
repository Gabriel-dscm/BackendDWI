package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "Cliente")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCliente")
    private Integer idCliente;

    @Column(name = "Nombres")
    private String nombres;

    @Column(name = "ApellidoPaterno")
    private String apellidoPaterno;

    @Column(name = "ApellidoMaterno")
    private String apellidoMaterno;

    @Column(name = "Email")
    private String email;

    @Column(name = "Contrasena")
    private String contrasena;

    @Column(name = "Estado")
    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaRegistro")
    private Date fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "IdRolCliente")
    private RolCliente rolCliente;
}
