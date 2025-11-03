package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LibroPalabraClave")
@Getter
@Setter
public class LibroPalabraClave {

    @EmbeddedId
    private LibroPalabraClaveId id;

    @ManyToOne
    @MapsId("idLibro")
    @JoinColumn(name = "IdLibro")
    private Libro libro;

    @ManyToOne
    @MapsId("idPalabraClave")
    @JoinColumn(name = "IdPalabraClave")
    private PalabraClave palabraClave;
}
