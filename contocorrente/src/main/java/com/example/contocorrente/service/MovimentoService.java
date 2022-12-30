package com.example.contocorrente.service;

import com.example.contocorrente.model.Movimento;
import com.example.contocorrente.repository.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class    MovimentoService {
    @Autowired
    private final MovimentoRepository movimentoRepository;

    @Autowired
    private final ContocorrenteService contocorrenteService;




    @Autowired
    public MovimentoService(MovimentoRepository movimentoRepository, ContocorrenteService contocorrenteService){
        this.movimentoRepository = movimentoRepository;
        this.contocorrenteService = contocorrenteService;
    }

    public void addMovimento(Movimento movimento){
        movimentoRepository.save(movimento);
    }

    public void prelievo(Movimento movimento){

            if(contocorrenteService.getContocorrenteById(movimento.getIdConto().getId()).isPresent())
            {
                movimentoRepository.save(movimento);

                Long conto = movimento.getIdConto().getId();
                double saldo;

                Optional<Double> sald = contocorrenteService.getSaldoById(conto);

                if (sald.isPresent()){
                    saldo = sald.get() - movimento.getImporto();
                    movimentoRepository.aggiornamento(conto, saldo);
                }
            }
            else {
                System.out.println("Il contocorrente inserito non esiste");
            }

    }

    public void versamento(Movimento movimento) {

        if(contocorrenteService.getContocorrenteById(movimento.getIdConto().getId()).isPresent()) {
            movimentoRepository.save(movimento);

            Long conto = movimento.getIdConto().getId();
            double saldo;

            Optional<Double> sald = contocorrenteService.getSaldoById(conto);

            if (sald.isPresent()){
                saldo = sald.get() + movimento.getImporto();
                movimentoRepository.aggiornamento(conto, saldo);
            }
        }
        else {
            System.out.println("Il contocorrente inserito non esiste");
        }

    }


    public List<Movimento> getAllMovimenti(){
        return movimentoRepository.findAll();
    }

    public Optional<Movimento> getMovimentoById(Long id){
        return  movimentoRepository.findById(id);
    }

    public void deleteMovimento(Long id) {
        try {
            movimentoRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
        }

    }

    public void updateMovimento(Long id, Movimento movimento) {
        try {
            movimentoRepository.deleteById(id);
            movimentoRepository.save(movimento);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Movimento> getUltimi5Movimenti(Long idConto){

       List<Movimento> movimenti = movimentoRepository.getMovimentiByIdConto(idConto);
       if(movimenti.size() < 5)
           return movimenti;

       return movimenti.subList(0, 5);

    }
}
