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

    @ManyToOne(fetch = FetchType.LAZY) // Ayuda a no traerlo si no se necesita
    @JoinColumn(name = "id_libro")
    // ✅ ESTA ES LA SOLUCIÓN:
    // Ignoramos TODAS las listas pesadas del libro para que el Update responda al instante.
    @JsonIgnoreProperties({"ejemplares", "autorLibros", "libroPalabraClaves", "dewey", "hibernateLazyInitializer", "handler"})
    private Libro libro;
}