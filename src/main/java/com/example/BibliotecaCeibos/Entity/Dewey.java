package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "Dewey")
@Getter
@Setter
public class Dewey {

    @Id
    @Column(name = "IdDewey")
    private Integer idDewey;

    @Column(name = "Categoria")
    private String categoria;

    @Column(name = "Descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "dewey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros;
}
