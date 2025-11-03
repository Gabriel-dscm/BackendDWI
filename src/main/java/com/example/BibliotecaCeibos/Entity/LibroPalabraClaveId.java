package com.example.BibliotecaCeibos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class LibroPalabraClaveId implements Serializable {
    @Column(name = "IdLibro")
    private Integer idLibro;

    @Column(name = "IdPalabraClave")
    private Integer idPalabraClave;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibroPalabraClaveId)) return false;
        LibroPalabraClaveId that = (LibroPalabraClaveId) o;
        return Objects.equals(idLibro, that.idLibro) && Objects.equals(idPalabraClave, that.idPalabraClave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLibro, idPalabraClave);
    }
}
