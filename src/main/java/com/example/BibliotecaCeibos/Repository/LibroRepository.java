package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    // ✅ CONSULTA MAESTRA: Trae Libro + Dewey + Autores + Palabras + Ejemplares en UN SOLO VIAJE
    @Query("SELECT DISTINCT l FROM Libro l " +
           "LEFT JOIN FETCH l.dewey " +
           "LEFT JOIN FETCH l.autorLibros al " +
           "LEFT JOIN FETCH al.autor " +
           "LEFT JOIN FETCH l.libroPalabraClaves lpc " +
           "LEFT JOIN FETCH lpc.palabraClave " +
           "LEFT JOIN FETCH l.ejemplares")
    List<Libro> findAllWithDetails();

    // ✅ BUSCADOR MAESTRO: Hace lo mismo pero filtrando
    @Query("SELECT DISTINCT l FROM Libro l " +
           "LEFT JOIN FETCH l.dewey " +
           "LEFT JOIN FETCH l.autorLibros al " +
           "LEFT JOIN FETCH al.autor " +
           "LEFT JOIN FETCH l.libroPalabraClaves lpc " +
           "LEFT JOIN FETCH lpc.palabraClave " +
           "WHERE (:titulo IS NULL OR l.titulo LIKE %:titulo%) " +
           "AND (:autor IS NULL OR al.autor.nombres LIKE %:autor% OR al.autor.apellidos LIKE %:autor%) " +
           "AND (:deweyId IS NULL OR l.dewey.idDewey = :deweyId) " +
           "AND (:editorial IS NULL OR l.editorial LIKE %:editorial%) " +
           "AND (:estado IS NULL OR EXISTS (SELECT e FROM l.ejemplares e WHERE e.estado = :estado))")
    List<Libro> searchLibros(String titulo, String autor, Integer deweyId, String editorial, String estado);

    @Query("SELECT DISTINCT l.editorial FROM Libro l")
    List<String> findDistinctEditoriales();
}
