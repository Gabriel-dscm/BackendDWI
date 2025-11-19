package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.Cliente;
import com.example.BibliotecaCeibos.Entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo,Integer>{
    List<Prestamo> findByCliente(Cliente cliente);

    @Query("SELECT p FROM Prestamo p " +
            "LEFT JOIN FETCH p.ejemplar e " +
            "LEFT JOIN FETCH e.libro l " +
            "WHERE p.cliente = :cliente AND (p.estado IS NULL OR p.estado = 'ACTIVO')")
    List<Prestamo> findListByClienteAndEstado(@Param("cliente") Cliente cliente, @Param("estado") String estado);

    @Query("SELECT p FROM Prestamo p " +
            "LEFT JOIN FETCH p.cliente c " +
            "LEFT JOIN FETCH p.ejemplar e " +
            "LEFT JOIN FETCH e.libro l " +
            "WHERE (p.estado IS NULL OR p.estado = :estado)")
    List<Prestamo> findAllWithDetails(@Param("estado") String estado);

    @Query("SELECT p FROM Prestamo p " +
            "LEFT JOIN FETCH p.cliente c " +
            "LEFT JOIN FETCH p.ejemplar e " +
            "LEFT JOIN FETCH e.libro l " +
            "WHERE (p.estado IS NULL OR p.estado = :estado) " +
            "AND (:clienteNombre IS NULL OR c.nombres LIKE %:clienteNombre% OR c.apellidoPaterno LIKE %:clienteNombre% OR c.apellidoMaterno LIKE %:clienteNombre%) " +
            "AND (:ejemplarInfo IS NULL OR l.titulo LIKE %:ejemplarInfo% OR e.codigoInterno LIKE %:ejemplarInfo%) " +
            "AND (:fechaPrestamo IS NULL OR DATE(p.fechaPrestamo) = :fechaPrestamo) " +
            "AND (:fechaDevolucion IS NULL OR DATE(p.fechaDevolucion) = :fechaDevolucion)")
    List<Prestamo> searchPrestamos(
            @Param("clienteNombre") String clienteNombre,
            @Param("ejemplarInfo") String ejemplarInfo,
            @Param("fechaPrestamo") LocalDate fechaPrestamo,
            @Param("fechaDevolucion") LocalDate fechaDevolucion,
            @Param("estado") String estado
    );

    @Query("SELECT p FROM Prestamo p " +
            "LEFT JOIN FETCH p.cliente c " +
            "LEFT JOIN FETCH p.ejemplar e " +
            "LEFT JOIN FETCH e.libro l " +
            "WHERE p.estado = 'DEVUELTO' " +
            "AND (:clienteNombre IS NULL OR c.nombres LIKE %:clienteNombre% OR c.apellidoPaterno LIKE %:clienteNombre%) " +
            "AND (:ejemplarInfo IS NULL OR l.titulo LIKE %:ejemplarInfo% OR e.codigoInterno LIKE %:ejemplarInfo%) " +
            "AND (:fechaPrestamo IS NULL OR DATE(p.fechaPrestamo) = :fechaPrestamo)")
    List<Prestamo> searchPrestamosDevueltos(
            @Param("clienteNombre") String clienteNombre,
            @Param("ejemplarInfo") String ejemplarInfo,
            @Param("fechaPrestamo") LocalDate fechaPrestamo
    );}