package com.example.contocorrente.model;

import jakarta.persistence.*;
import java.util.Date;


@Table(name = "movimento")
@Entity
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "descrzione", nullable = false)
    private String descrizione;

    @Column(name = "importo", nullable = false)
    private double importo;

    @Column(name = "data", nullable = false)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "id_conto", nullable = false)
    private Contocorrente idConto;

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
