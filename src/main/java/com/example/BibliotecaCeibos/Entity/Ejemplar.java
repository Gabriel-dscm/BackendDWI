package com.example.BibliotecaCeibos.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Ejemplar")
@Getter
@Setter
public class Ejemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejemplar")
    private Integer idEjemplar;

    @Column(name = "codigo_interno")
    private String codigoInterno;

    @Column(name = "estado_ejemplar")
    private String estadoEjemplar;

    @Column(name = "estado")
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro")
    // ✅ CAMBIO AQUÍ: 
    // Borré "autorLibros" de la lista de ignorados. 
    // Mantenemos "ejemplares" y "libroPalabraClaves" ignorados para evitar lentitud.
    @JsonIgnoreProperties({"ejemplares", "libroPalabraClaves", "dewey", "hibernateLazyInitializer", "handler"})
    private Libro libro;
}