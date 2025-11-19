package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Integer>{

    @Query("SELECT l FROM Libro l LEFT JOIN FETCH l.dewey LEFT JOIN FETCH l.autorLibros al LEFT JOIN FETCH al.autor")
    List<Libro> findAllWithDetails();

    @Query("SELECT DISTINCT l FROM Libro l " +
            "LEFT JOIN FETCH l.dewey d " +
            "LEFT JOIN FETCH l.autorLibros al LEFT JOIN FETCH al.autor a " +
            "LEFT JOIN l.libroPalabraClaves lpc LEFT JOIN lpc.palabraClave pc " +
            "LEFT JOIN l.ejemplares e " +
            "WHERE " +
            "(COALESCE(:autor, '') = 'UNIVERSAL_SEARCH_FLAG' AND (" +
            "  l.titulo LIKE %:titulo% OR " +
            "  pc.descripcion LIKE %:titulo% OR " +
            "  a.nombres LIKE %:titulo% OR " +
            "  a.apellidos LIKE %:titulo% OR " +
            "  d.categoria LIKE %:titulo% OR " +
            "  d.descripcion LIKE %:titulo%" +
            ")) " +
            "OR " +
            "(COALESCE(:autor, '') != 'UNIVERSAL_SEARCH_FLAG' AND (" +
            "  (:titulo IS NULL OR l.titulo LIKE %:titulo% OR pc.descripcion LIKE %:titulo%) AND " +
            "  (:autor IS NULL OR a.nombres LIKE %:autor% OR a.apellidos LIKE %:autor%) AND " +
            "  (:deweyId IS NULL OR d.idDewey = :deweyId) AND " +
            "  (:editorial IS NULL OR l.editorial LIKE %:editorial%) AND " +
            "  (:estado IS NULL OR e.estado = :estado)" +
            "))")
    List<Libro> searchLibros(
            @Param("titulo") String titulo,
            @Param("autor") String autor,
            @Param("deweyId") Integer deweyId,
            @Param("editorial") String editorial,
            @Param("estado") String estado
    );

    @Query("SELECT DISTINCT l.editorial FROM Libro l WHERE l.editorial IS NOT NULL AND l.editorial != '' ORDER BY l.editorial ASC")
    List<String> findDistinctEditoriales();
}