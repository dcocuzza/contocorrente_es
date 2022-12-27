package com.example.contocorrente.repository;

import com.example.contocorrente.model.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

    @Query(value = "UPDATE movimento SET importo = ?2 WHERE id_conto = ?1", nativeQuery = true)
    void prelievo(Long id_conto, double importo);

    @Query(value = "UPDATE movimento SET importo = ?2 WHERE id_conto = ?1", nativeQuery = true)
    void versamento(Long id_conto, double importo);
    List<Movimento> getMovimentoById(Long id_conto);
}
