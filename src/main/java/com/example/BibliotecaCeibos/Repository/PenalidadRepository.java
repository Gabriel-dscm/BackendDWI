package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.Penalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface PenalidadRepository extends JpaRepository<Penalidad,Integer>{

    @Query("SELECT p FROM Penalidad p " +
            "LEFT JOIN FETCH p.cliente c " +
            "LEFT JOIN FETCH p.devolucion d " +
            "WHERE (:clienteNombre IS NULL OR c.nombres LIKE %:clienteNombre% OR c.apellidoPaterno LIKE %:clienteNombre%) " +
            "AND (:estado IS NULL OR p.estado = :estado) " +
            "AND (:fecha IS NULL OR DATE(p.fechaPenalidad) = :fecha)")
    List<Penalidad> searchPenalidades(
            @Param("clienteNombre") String clienteNombre,
            @Param("estado") String estado,
            @Param("fecha") LocalDate fecha
    );
}