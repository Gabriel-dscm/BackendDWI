package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.AutorLibro;
import com.example.BibliotecaCeibos.Entity.AutorLibroId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorLibroRepository extends JpaRepository<AutorLibro, AutorLibroId> {
}
