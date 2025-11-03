package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
