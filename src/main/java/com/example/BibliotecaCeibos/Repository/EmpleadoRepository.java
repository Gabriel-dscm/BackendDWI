package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado,Integer>{
    Optional<Empleado> findByEmail(String email);
}