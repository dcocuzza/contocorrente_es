package com.example.contocorrente.api;

import com.example.contocorrente.model.Anagrafica;
import com.example.contocorrente.service.AnagraficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("api/v1/anagrafica")
@RestController
public class AnagraficaController {
    @Autowired
    private final AnagraficaService anagraficaService;

    @Autowired
    public AnagraficaController(AnagraficaService anagraficaService) {
        this.anagraficaService = anagraficaService;
    }

    @PostMapping
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Rome")
    public void addAnagrafica(@NonNull @RequestBody Anagrafica anagrafica){
        anagraficaService.addAnagrafica(anagrafica);
    }

    @GetMapping
    public List<Anagrafica> getAllAnagrafiche() {
        return anagraficaService.getAllAnagrafiche();
    }

    @GetMapping(path = "/{id}")
    public Anagrafica getAnagraficaById(@PathVariable("id") Long id){
        return anagraficaService.getAnagraficaById(id);
    }

    /*@GetMapping(path = "/{id}")
    public ResponseEntity<?> getAnagraficaById(@PathVariable("id") Long id){
        return anagraficaService.getAnagraficaById(id)
                                .map(anagrafica -> ResponseEntity
                                        .ok()
                                        .body(anagrafica))
                .orElse(ResponseEntity.notFound().build());
    }*/

    @DeleteMapping(path = "/{id}")
    public void deleteAnagraficaById(@PathVariable("id") Long id){
        anagraficaService.deleteAnagraficaById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateAnagrafica(@PathVariable("id") Long id, @NonNull @RequestBody Anagrafica anagraficaToUpdate){
        anagraficaService.updateAnagrafica(id, anagraficaToUpdate);
    }
}
