package com.example.contocorrente.service;

import com.example.contocorrente.model.Movimento;
import com.example.contocorrente.repository.ContocorrenteRepository;
import com.example.contocorrente.repository.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovimentoService {
    @Autowired
    private final MovimentoRepository movimentoRepository;




    @Autowired
    public MovimentoService(MovimentoRepository movimentoRepository){
        this.movimentoRepository = movimentoRepository;

    }

    public void addMovimento(Movimento movimento){
        movimentoRepository.save(movimento);
    }

    public void prelievo(Movimento movimento){
        movimentoRepository.save(movimento);
        Long conto = movimento.getIdConto().getId();

        double saldo;

        List<Double> sald = movimentoRepository.getSaldoById(conto);


        saldo = sald.get(0) - movimento.getImporto();
        movimentoRepository.aggiornamento(conto, saldo);
    }

    public void versamento(Movimento movimento){
        movimentoRepository.save(movimento);

        Long conto = movimento.getIdConto().getId();
        double saldo;

        List<Double> sald = movimentoRepository.getSaldoById(conto);


        saldo = sald.get(0) + movimento.getImporto();
        movimentoRepository.aggiornamento(conto, saldo);

    }


    public List<Movimento> getAllMovimenti(){
        return movimentoRepository.findAll();
    }

    public Optional<Movimento> getMovimentoById(Long id){
        return  movimentoRepository.findById(id);
    }

    public void deleteMovimento(Long id) {
        movimentoRepository.deleteById(id);
    }

    public void updateMovimento(Long id, Movimento movimento) {
        movimentoRepository.deleteById(id);
        movimentoRepository.save(movimento);
    }

    public List<Movimento> getUltimi5Movimenti(Long idConto){

        //return movimentoRepository.getMovimentiByIdConto(idConto);

       List<Movimento> movimenti = movimentoRepository.getMovimentiByIdConto(idConto);
       if(movimenti.size() < 5)
           return movimenti;

       return movimenti.subList(0, 5);

        //return movimenti;


    }
}
