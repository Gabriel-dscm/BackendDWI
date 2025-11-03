package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AutorLibro")
@Getter
@Setter
public class AutorLibro {

    @EmbeddedId
    private AutorLibroId id;

    @ManyToOne
    @MapsId("idAutor")
    @JoinColumn(name = "IdAutor")
    private Autor autor;

    @ManyToOne
    @MapsId("idLibro")
    @JoinColumn(name = "IdLibro")
    private Libro libro;
}
