package com.example.contocorrente.service;

import com.example.contocorrente.exception.IdNotFoundException;
import com.example.contocorrente.model.Anagrafica;
import com.example.contocorrente.repository.AnagraficaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnagraficaService {
    private final AnagraficaRepository anagraficaRepository;

    public AnagraficaService(AnagraficaRepository anagraficaRepository){
        this.anagraficaRepository = anagraficaRepository;
    }

    public void addAnagrafica(Anagrafica anagrafica){
        anagraficaRepository.save(anagrafica);
    }

    public List<Anagrafica> getAllAnagrafiche(){
        return anagraficaRepository.findAll();
    }

    public Anagrafica getAnagraficaById(Long id){
        return  anagraficaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Id errato") );
    }

    public void deleteAnagraficaById(Long id) {
        try {
            anagraficaRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new IdNotFoundException("L'anagrafica non esiste(id errato)");
        }

    }

    public void updateAnagrafica(Long id, Anagrafica anagrafica) {
        try {
            anagraficaRepository.deleteById(id);
            anagraficaRepository.save(anagrafica);
        }
        catch (EmptyResultDataAccessException e){
            throw new IdNotFoundException("L'anagrafica non esiste(id errato)");
        }

    }
}
