package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "Empleado")
@Getter
@Setter
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEmpleado")
    private Integer idEmpleado;

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

    @Column(name = "CodigoEmpleado")
    private String codigoEmpleado;

    @Column(name = "Estado")
    private String estado;

    @Column(name = "Cargo")
    private String cargo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaRegistro")
    private Date fechaRegistro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaRetiro")
    private Date fechaRetiro;

    @ManyToOne
    @JoinColumn(name = "IdRolEmpleado")
    private RolEmpleado rolEmpleado;
}
