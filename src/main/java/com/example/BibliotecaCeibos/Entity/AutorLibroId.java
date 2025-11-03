package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class AutorLibroId implements Serializable {
    @Column(name = "IdAutor")
    private Integer idAutor;

    @Column(name = "IdLibro")
    private Integer idLibro;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AutorLibroId)) return false;
        AutorLibroId that = (AutorLibroId) o;
        return Objects.equals(idAutor, that.idAutor) && Objects.equals(idLibro, that.idLibro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAutor, idLibro);
    }
}
