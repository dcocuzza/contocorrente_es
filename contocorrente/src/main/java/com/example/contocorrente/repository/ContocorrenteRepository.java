package com.example.contocorrente.repository;

import com.example.contocorrente.model.Contocorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContocorrenteRepository extends JpaRepository<Contocorrente, Long> {

   // @Query(value = "Select saldo from contocorrente where ")
    List<Double> getSaldoById(Long id);
}
