package com.example.contocorrente.repository;

import com.example.contocorrente.model.Contocorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContocorrenteRepository extends JpaRepository<Contocorrente, Long> {

   @Query(value = "SELECT saldo FROM contocorrente WHERE id = ?1", nativeQuery = true)
   Optional<Double> getSaldoById(Long id);
}
