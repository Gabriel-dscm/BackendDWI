package com.example.BibliotecaCeibos.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "libro_palabra_clave")
@Getter
@Setter
public class LibroPalabraClave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro_palabra_clave")
    private Integer idLibroPalabraClave;

    @ManyToOne
    @JoinColumn(name = "id_libro")
    @JsonIgnoreProperties("libroPalabraClaves")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "id_palabra_clave")
    @JsonIgnoreProperties("libroPalabraClaves")
    private PalabraClave palabraClave;
}