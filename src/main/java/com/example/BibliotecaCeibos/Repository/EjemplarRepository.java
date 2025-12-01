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

    // ✅ OPTIMIZACIÓN: Trae el Ejemplar Y los datos del Libro en 1 sola consulta
    @Query("SELECT e FROM Ejemplar e LEFT JOIN FETCH e.libro")
    List<Ejemplar> findAllWithDetails();

    Optional<Ejemplar> findFirstByLibroAndEstado(Libro libro, String estado);
}