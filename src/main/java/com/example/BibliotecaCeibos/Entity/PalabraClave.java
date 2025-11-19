package com.example.BibliotecaCeibos.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "palabra_clave")
@Getter
@Setter
public class PalabraClave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_palabra_clave")
    private Integer idPalabraClave;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "palabraClave", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("palabraClave")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<LibroPalabraClave> libroPalabraClaves;
}