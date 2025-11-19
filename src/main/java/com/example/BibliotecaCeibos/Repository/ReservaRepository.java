package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.Cliente;
import com.example.BibliotecaCeibos.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva,Integer>{
    List<Reserva> findByCliente(Cliente cliente);

    @Query("SELECT r FROM Reserva r " +
            "LEFT JOIN FETCH r.cliente c " +
            "LEFT JOIN FETCH r.ejemplar e " +
            "LEFT JOIN FETCH e.libro l " +
            "WHERE r.estado IS NULL OR r.estado != 'COMPLETADA'")
    List<Reserva> findAllWithDetails();

    @Query("SELECT r FROM Reserva r " +
            "LEFT JOIN FETCH r.cliente c " +
            "LEFT JOIN FETCH r.ejemplar e " +
            "LEFT JOIN FETCH e.libro l " +
            "WHERE (r.estado IS NULL OR r.estado != 'COMPLETADA') " +
            "AND (:clienteNombre IS NULL OR c.nombres LIKE %:clienteNombre% OR c.apellidoPaterno LIKE %:clienteNombre% OR c.apellidoMaterno LIKE %:clienteNombre%) " +
            "AND (:ejemplarInfo IS NULL OR l.titulo LIKE %:ejemplarInfo% OR e.codigoInterno LIKE %:ejemplarInfo%) " +
            "AND (:fechaReserva IS NULL OR DATE(r.fechaReserva) = :fechaReserva) " +
            "AND (:fechaExpiracion IS NULL OR DATE(r.fechaExpiracion) = :fechaExpiracion)")
    List<Reserva> searchReservas(
            @Param("clienteNombre") String clienteNombre,
            @Param("ejemplarInfo") String ejemplarInfo,
            @Param("fechaReserva") LocalDate fechaReserva,
            @Param("fechaExpiracion") LocalDate fechaExpiracion
    );

    @Query("SELECT r FROM Reserva r " +
            "LEFT JOIN FETCH r.cliente c " +
            "LEFT JOIN FETCH r.ejemplar e " +
            "LEFT JOIN FETCH e.libro l " +
            "WHERE r.estado = 'COMPLETADA' " +
            "AND (:clienteNombre IS NULL OR c.nombres LIKE %:clienteNombre% OR c.apellidoPaterno LIKE %:clienteNombre% OR c.apellidoMaterno LIKE %:clienteNombre%) " +
            "AND (:ejemplarInfo IS NULL OR l.titulo LIKE %:ejemplarInfo% OR e.codigoInterno LIKE %:ejemplarInfo%) " +
            "AND (:fechaReserva IS NULL OR DATE(r.fechaReserva) = :fechaReserva) " +
            "AND (:fechaExpiracion IS NULL OR DATE(r.fechaExpiracion) = :fechaExpiracion)")
    List<Reserva> searchReservasCompletadas(
            @Param("clienteNombre") String clienteNombre,
            @Param("ejemplarInfo") String ejemplarInfo,
            @Param("fechaReserva") LocalDate fechaReserva,
            @Param("fechaExpiracion") LocalDate fechaExpiracion
    );
    @Query("SELECT r FROM Reserva r " +
            "LEFT JOIN FETCH r.ejemplar e " +
            "LEFT JOIN FETCH e.libro l " +
            "WHERE r.cliente = :cliente AND (r.estado IS NULL OR r.estado != 'COMPLETADA') " +
            "ORDER BY r.fechaReserva DESC")
    List<Reserva> findReservasActivasByCliente(@Param("cliente") Cliente cliente);
}