package com.example.BibliotecaCeibos.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty; // <-- 1. IMPORTA ESTE
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
    @Column(name = "id_dewey")
    private Integer idDewey;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "dewey", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("dewey")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Libro> libros;
}