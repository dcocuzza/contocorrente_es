package com.example.contocorrente.api;

import com.example.contocorrente.model.Contocorrente;
import com.example.contocorrente.service.ContocorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/conto")
@RestController
public class ContocorrenteController {
    @Autowired
    private final ContocorrenteService contocorrenteService;

    @Autowired
    public ContocorrenteController(ContocorrenteService contocorrenteService) {
        this.contocorrenteService = contocorrenteService;
    }

    @PostMapping
    public void addContocorrente(@NonNull @RequestBody Contocorrente contocorrente) {
        contocorrenteService.addContocorrente(contocorrente);
    }

    @GetMapping
    public List<Contocorrente> getAllConticorrenti() {
        return contocorrenteService.getAllConticorrenti();
    }

    //@GetMapping(path = "/prelievo/{id_conto}")

    @GetMapping(path = "/{id}")
    public Contocorrente getContocorrenteById(@PathVariable("id") Long id){
        return contocorrenteService.getContocorrenteById(id);
    }

    @GetMapping(path = "/saldo/{id}")
    public Double getSaldoById(@PathVariable("id") Long id){
        return contocorrenteService.getSaldoById(id).orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteContocorrenteById(@PathVariable("id") Long id){
        contocorrenteService.deleteContocorrente(id);
    }

    @PutMapping(path = "/{id}")
    public void updateContocorrente(@PathVariable("id") Long id, @NonNull @RequestBody Contocorrente contocorrenteToUpdate){
        contocorrenteService.updateContocorrente(id, contocorrenteToUpdate);
    }
}
