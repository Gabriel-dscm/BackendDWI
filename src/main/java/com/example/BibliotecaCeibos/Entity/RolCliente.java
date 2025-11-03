package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RolCliente")
@Getter
@Setter
public class RolCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRolCliente")
    private Integer idRolCliente;

    @Column(name = "Descripcion")
    private String descripcion;
}
