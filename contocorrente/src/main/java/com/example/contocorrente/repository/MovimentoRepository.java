package com.example.contocorrente.repository;

import com.example.contocorrente.model.Contocorrente;
import com.example.contocorrente.model.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE contocorrente SET saldo = ?2 WHERE id = ?1", nativeQuery = true)
    void aggiornamento(Long id_conto, double importo);

    @Query(value = "SELECT * FROM movimento WHERE id_conto = ?1 ORDER BY data DESC", nativeQuery = true)
    List<Movimento> getMovimentiByIdConto(Long idConto);


  /*  @Query(value = "SELECT saldo FROM contocorrente WHERE id = ?1", nativeQuery = true)
    Optional<Double> getSaldoById(Long id);*/


}
