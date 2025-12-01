package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.Ejemplar;
import com.example.BibliotecaCeibos.Entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Integer> {

    // ✅ OPTIMIZACIÓN AGRESIVA:
    // Traemos el Ejemplar y su Libro.
    // OJO: Si la tabla de ejemplares muestra el Autor, descomenta la linea de abajo.
    @Query("SELECT DISTINCT e FROM Ejemplar e " +
           "LEFT JOIN FETCH e.libro l " +
           "LEFT JOIN FETCH l.autorLibros al " + // Descomenta si necesitas mostrar el autor en la tabla de ejemplares
           "LEFT JOIN FETCH al.autor " + 
           "ORDER BY e.idEjemplar DESC") // Ordenar para que se vea mejor
    List<Ejemplar> findAllWithDetails();

    Optional<Ejemplar> findFirstByLibroAndEstado(Libro libro, String estado);
}