package com.example.BibliotecaCeibos.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.Set; // <--- CAMBIO IMPORTANTE: Usar Set en vez de List

@Entity
@Table(name = "Libro")
@Getter
@Setter
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Integer idLibro;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "editorial")
    private String editorial;

    @Temporal(TemporalType.DATE)
    @Column(name = "anio_publicacion")
    private Date anioPublicacion;

    @Column(name = "isbn")
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "id_dewey")
    private Dewey dewey;

    // CAMBIO 1: List -> Set
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("libro")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) 
    private Set<Ejemplar> ejemplares;

    // CAMBIO 2: List -> Set
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("libro")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<AutorLibro> autorLibros;

    // CAMBIO 3: List -> Set
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("libro")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<LibroPalabraClave> libroPalabraClaves;
}
