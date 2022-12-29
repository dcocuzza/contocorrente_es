package com.example.contocorrente.service;

import com.example.contocorrente.model.Contocorrente;
import com.example.contocorrente.repository.ContocorrenteRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public List<Contocorrente> getAllConticorrenti(){
        return contocorrenteRepository.findAll();
    }

    public Optional<Contocorrente> getContocorrenteById(Long id){
        return  contocorrenteRepository.findById(id);
    }

    public void deleteContocorrente(Long id) {
        try {
            contocorrenteRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
        }

    }

    public void updateContocorrente(Long id, Contocorrente contocorrente) {
        try {
            contocorrenteRepository.deleteById(id);
            contocorrenteRepository.save(contocorrente);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
        }
        catch (ConstraintViolationException e){
            System.out.println(e.getMessage());
        }

    }

    public Optional<Double> getSaldoById(Long id){
        return contocorrenteRepository.getSaldoById(id);
    }
}
