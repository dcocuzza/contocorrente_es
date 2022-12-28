package com.example.contocorrente.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Table(name = "anagrafica")
@Entity
public class Anagrafica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    public Anagrafica() {
    }

    public Anagrafica(Long id){
        this.id = id;
    }

    @Column(name = "cf", nullable = false)
    private String cf;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Rome")
    //Nel JSON mettere il nome dell'attributo in questo caso dataNascita : "dd-MM-yyyy"
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "data_nascita", nullable = false)
    private Date dataNascita;


    @OneToMany(mappedBy = "intestatario")
    private Set<Contocorrente> intestatario;

    @OneToMany(mappedBy = "cointestatario")
    private Set<Contocorrente> cointestatario;

   //I set e get delle join vanno sulla classe che svolge il ruolo 'molti'

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }
}
