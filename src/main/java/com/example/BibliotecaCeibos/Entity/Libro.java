package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Libro")
@Getter
@Setter
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLibro")
    private Integer idLibro;

    @Column(name = "Titulo")
    private String titulo;

    @Column(name = "Editorial")
    private String editorial;

    @Temporal(TemporalType.DATE)
    @Column(name = "AnioPublicacion")
    private Date anioPublicacion;

    @Column(name = "ISBN")
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "IdDewey")
    private Dewey dewey;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ejemplar> ejemplares;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AutorLibro> autorLibros;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LibroPalabraClave> libroPalabraClaves;
}
