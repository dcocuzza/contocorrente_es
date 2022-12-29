package com.example.contocorrente.service;

import com.example.contocorrente.model.Contocorrente;
import com.example.contocorrente.repository.ContocorrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addContocorrente(Contocorrente contocorrente){
        contocorrenteRepository.save(contocorrente);
    }

    public List<Contocorrente> getAllConticorrenti(){
        return contocorrenteRepository.findAll();
    }

    public Optional<Contocorrente> getContocorrenteById(Long id){
        return  contocorrenteRepository.findById(id);
    }

    public void deleteContocorrente(Long id) {
        contocorrenteRepository.deleteById(id);
    }

    public void updateContocorrente(Long id, Contocorrente contocorrente) {
        contocorrenteRepository.deleteById(id);
        contocorrenteRepository.save(contocorrente);
    }

    public Optional<Double> getSaldoById(Long id){
        return contocorrenteRepository.getSaldoById(id);
    }
}
