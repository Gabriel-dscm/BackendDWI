package com.example.BibliotecaCeibos.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "autor_libro")
@Getter
@Setter
public class AutorLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor_libro")
    private Integer idAutorLibro;


    @ManyToOne
    @JoinColumn(name = "id_autor")
    @JsonIgnoreProperties("autorLibros")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "id_libro")
    @JsonIgnoreProperties("autorLibros")
    private Libro libro;
}