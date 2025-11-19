package com.example.BibliotecaCeibos.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ReporteDTO {

    private String tipo;
    private Date fechaInicio;
    private Date fechaFin;
    private String clienteNombre;
    private String libroTitulo;
    private String estadoFinal;
}