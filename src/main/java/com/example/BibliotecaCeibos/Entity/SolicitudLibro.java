package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SolicitudLibro")
@Getter
@Setter
public class SolicitudLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSolicitud")
    private Integer idSolicitud;

    @Column(name = "Titulo")
    private String titulo;

    @Column(name = "Autor")
    private String autor;

    @Column(name = "Editorial")
    private String editorial;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "Comentario")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "IdCliente")
    private Cliente cliente;
}
