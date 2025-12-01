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

    // ✅ OPTIMIZACIÓN CORREGIDA:
    // Ahora hacemos FETCH también de los Autores, porque tu tabla del frontend los necesita.
    @Query("SELECT DISTINCT e FROM Ejemplar e " +
           "LEFT JOIN FETCH e.libro l " +
           "LEFT JOIN FETCH l.autorLibros al " +  // <--- DESCOMENTADO
           "LEFT JOIN FETCH al.autor " +          // <--- DESCOMENTADO
           "ORDER BY e.idEjemplar DESC")
    List<Ejemplar> findAllWithDetails();

    Optional<Ejemplar> findFirstByLibroAndEstado(Libro libro, String estado);
}