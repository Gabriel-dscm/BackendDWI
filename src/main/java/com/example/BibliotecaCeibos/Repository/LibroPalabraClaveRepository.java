package com.example.BibliotecaCeibos.Repository;

import com.example.BibliotecaCeibos.Entity.LibroPalabraClave;
import com.example.BibliotecaCeibos.Entity.LibroPalabraClaveId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroPalabraClaveRepository extends JpaRepository<LibroPalabraClave, LibroPalabraClaveId> {
}
