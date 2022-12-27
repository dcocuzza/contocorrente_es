package com.example.contocorrente.repository;

import com.example.contocorrente.model.Anagrafica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnagraficaRepository extends JpaRepository<Anagrafica, Long> {
}
