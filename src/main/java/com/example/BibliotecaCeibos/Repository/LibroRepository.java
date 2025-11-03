package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro,Integer>{
}
