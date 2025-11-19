package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.Ejemplar;
import com.example.BibliotecaCeibos.Entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EjemplarRepository extends JpaRepository<Ejemplar,Integer>{
    Optional<Ejemplar> findFirstByLibroAndEstado(Libro libro, String estado);
}