package com.example.contocorrente.api;

import com.example.contocorrente.model.Movimento;
import com.example.contocorrente.service.MovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/movimento")
@RestController
public class MovimentoController {
    @Autowired
    private final MovimentoService movimentoService;

    @Autowired
    public MovimentoController(MovimentoService movimentoService) {
        this.movimentoService = movimentoService;
    }

    @PostMapping()
    public void addMovimento(@NonNull @RequestBody Movimento movimento){
        movimentoService.addMovimento(movimento);
    }

    @PostMapping(path = "/prelievo")
    public void prelievo(@NonNull @RequestBody Movimento movimento){
        movimentoService.prelievo(movimento);
    }

    @PostMapping(path = "/versamento")
    public void versamento(@NonNull @RequestBody Movimento movimento){
        movimentoService.versamento(movimento);
    }

    @GetMapping
    public List<Movimento> getAllMovimento() {
        return movimentoService.getAllMovimenti();
    }

    @GetMapping(path = "/{id}")
    public Movimento getMovimentoById(@PathVariable("id") Long id){
        return movimentoService.getMovimentoById(id).orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteMovimentoById(@PathVariable("id") Long id){
        movimentoService.deleteMovimento(id);
    }

    @PutMapping(path = "/{id}")
    public void updateMovimento(@PathVariable("id") Long id, @NonNull @RequestBody Movimento movimentoToUpdate){
        movimentoService.updateMovimento(id, movimentoToUpdate);
    }

    @GetMapping("/ultimi/{idConto}")
    public List<Movimento> getUltimi5Movimenti(@PathVariable("idConto") Long idConto) {
        return movimentoService.getUltimi5Movimenti(idConto);
    }

}
