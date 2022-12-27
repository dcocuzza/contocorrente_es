package com.example.contocorrente.service;

import com.example.contocorrente.model.Movimento;
import com.example.contocorrente.repository.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Movimento> getAllMovimenti(){
        return movimentoRepository.findAll();
    }

    public Optional<Movimento> getMovimentoById(Long id){
        return  movimentoRepository.findById(id);
    }

    public void deleteMovimento(Long id) {
        movimentoRepository.deleteById(id);
    }

    public void updateAnagrafica(Long id, Movimento movimento) {
        movimentoRepository.deleteById(id);
        movimentoRepository.save(movimento);
    }

    public List<Movimento> getMovimentiById(Long id_conto){
        List<Movimento> movimenti = movimentoRepository.getMovimentoById(id_conto);

        if(movimenti.size() <= 5)
            return movimenti;

        List<Movimento> ultimi = new ArrayList<>();

        int i = 0;

        for(Movimento m : movimenti){
            if(i >= 5)
                break;
            i++;
            ultimi.add(m);
        }

        return ultimi;
    }
}
