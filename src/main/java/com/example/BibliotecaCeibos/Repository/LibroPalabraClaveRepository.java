package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.LibroPalabraClave;
// No más LibroPalabraClaveId
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroPalabraClaveRepository extends JpaRepository<LibroPalabraClave, Integer> {
}