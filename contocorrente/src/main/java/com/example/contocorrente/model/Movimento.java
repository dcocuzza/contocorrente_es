package com.example.contocorrente.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Date;


@Table(name = "movimento")
@Entity
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Movimento(){

    }

    public Movimento(Long id) {
        this.id = id;
    }

    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    @Column(name = "importo", nullable = false)
    private double importo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "data", nullable = false)
    private Date data;


    @ManyToOne
    @JoinColumn(name = "id_conto", nullable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Contocorrente idConto;

    public Contocorrente getIdConto() {
        return idConto;
    }

    public void setIdConto(Contocorrente idConto) {
        this.idConto = idConto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
