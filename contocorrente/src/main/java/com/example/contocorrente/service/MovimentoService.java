package com.example.contocorrente.service;

import com.example.contocorrente.exception.IdNotFoundException;
import com.example.contocorrente.exception.IntestatarioNotFoundException;
import com.example.contocorrente.model.Movimento;
import com.example.contocorrente.repository.MovimentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class    MovimentoService {

    private final MovimentoRepository movimentoRepository;

    private final ContocorrenteService contocorrenteService;

    public MovimentoService(MovimentoRepository movimentoRepository, ContocorrenteService contocorrenteService){

        this.movimentoRepository = movimentoRepository;
        this.contocorrenteService = contocorrenteService;

    }

    public void addMovimento(Movimento movimento){
        movimentoRepository.save(movimento);
    }

    public void prelievo(Movimento movimento){

                movimentoRepository.save(movimento);

                Long conto = movimento.getIdConto().getId();
                double saldo;

                Optional<Double> sald = contocorrenteService.getSaldoById(conto);

                if (sald.isPresent()){
                    saldo = sald.get() - movimento.getImporto();
                    movimentoRepository.aggiornamento(conto, saldo);
                }


    }

    public void versamento(Movimento movimento) {

            movimentoRepository.save(movimento);

            Long conto = movimento.getIdConto().getId();
            double saldo;

            Optional<Double> sald = contocorrenteService.getSaldoById(conto);

            if (sald.isPresent()){
                saldo = sald.get() + movimento.getImporto();
                movimentoRepository.aggiornamento(conto, saldo);
            }


    }


    public List<Movimento> getAllMovimenti(){
        return movimentoRepository.findAll();
    }

    public Movimento getMovimentoById(Long id){
        return  movimentoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Id errato") );
    }

    public void deleteMovimento(Long id) {
        try {
            movimentoRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new IdNotFoundException("Il movimento non esiste(id errato)");
        }

    }

    public void updateMovimento(Long id, Movimento movimento) {
        try {
            movimentoRepository.deleteById(id);
            movimentoRepository.save(movimento);
        }
        catch (EmptyResultDataAccessException e){
            throw new IdNotFoundException("Il movimento non esiste(id errato)");
        }
        catch (DataIntegrityViolationException e){
            throw new IntestatarioNotFoundException("Id conto corrente errato");
        }
    }

    public List<Movimento> getUltimi5Movimenti(Long idConto){

       List<Movimento> movimenti = movimentoRepository.getMovimentiByIdConto(idConto);
       if(movimenti.size() < 5)
           return movimenti;

       return movimenti.subList(0, 5);

    }
}
