package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RolEmpleado")
@Getter
@Setter
public class RolEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRolEmpleado")
    private Integer idRolEmpleado;

    @Column(name = "Descripcion")
    private String descripcion;
}
