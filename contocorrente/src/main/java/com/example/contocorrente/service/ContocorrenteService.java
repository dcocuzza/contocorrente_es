package com.example.contocorrente.service;

import com.example.contocorrente.exception.IdNotFoundException;
import com.example.contocorrente.exception.IntestatarioNotFoundException;
import com.example.contocorrente.model.Contocorrente;
import com.example.contocorrente.repository.ContocorrenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class ContocorrenteService {
    @Autowired
    private final ContocorrenteRepository contocorrenteRepository;

    @Autowired
    public ContocorrenteService(ContocorrenteRepository contocorrenteRepository){
        this.contocorrenteRepository = contocorrenteRepository;
    }

    public void addContocorrente(Contocorrente contocorrente) {
        try {
            contocorrenteRepository.save(contocorrente);
        }
        catch (DataIntegrityViolationException e){
           // e.printStackTrace();
            throw new IntestatarioNotFoundException("Id intestatario errato");
        }

    }

    public List<Contocorrente> getAllConticorrenti(){
        return contocorrenteRepository.findAll();
    }

    public Contocorrente getContocorrenteById(Long id){
        return  contocorrenteRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Id errato") );
    }

    public void deleteContocorrente(Long id) {
            contocorrenteRepository.deleteById(id);
    }

    public void updateContocorrente(Long id, Contocorrente contocorrente) {
        try {
            contocorrenteRepository.deleteById(id);
            contocorrenteRepository.save(contocorrente);
        }
        catch (DataIntegrityViolationException e){
            throw new IntestatarioNotFoundException("Id intestatario errato");
        }
        catch (EmptyResultDataAccessException e){
            throw new IdNotFoundException("Il conto non esiste(id errato)");
        }

    }

    public Optional<Double> getSaldoById(Long id){
        return contocorrenteRepository.getSaldoById(id);
    }
}
