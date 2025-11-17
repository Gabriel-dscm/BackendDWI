package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Integer>{

    @Query("SELECT l FROM Libro l LEFT JOIN FETCH l.dewey LEFT JOIN FETCH l.autorLibros al LEFT JOIN FETCH al.autor")
    List<Libro> findAllWithDetails();
}