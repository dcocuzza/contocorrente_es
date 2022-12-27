package com.example.contocorrente.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "contocorrente")
@Entity
public class Contocorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "iban", nullable = false, unique = true)
    private String iban;

    @Column(name = "abi", nullable = false, unique = true)
    private String abi;

    @Column(name = "cab", nullable = false)
    private String cab;

    @Column(name = "numero_cc", nullable = false, unique = true)
    private String numeroCC;

    @Column(name = "data_apertura", nullable = false)
    private Date dataApertura;

    @Column(name = "data_chiusura")
    private Date dataChiusura;

    @Column(name = "saldo", nullable = false)
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "intestatario", nullable = false)
    private Anagrafica intestatario;

    @ManyToOne
    @JoinColumn(name = "cointestatario")
    private Anagrafica cointestatario;

    @OneToMany(mappedBy = "idConto")
    private Set<Movimento> idConto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAbi() {
        return abi;
    }

    public void setAbi(String abi) {
        this.abi = abi;
    }

    public String getCab() {
        return cab;
    }

    public void setCab(String cab) {
        this.cab = cab;
    }

    public String getNumeroCC() {
        return numeroCC;
    }

    public void setNumeroCC(String numeroCC) {
        this.numeroCC = numeroCC;
    }

    public Date getDataApertura() {
        return dataApertura;
    }

    public void setDataApertura(Date dataApertura) {
        this.dataApertura = dataApertura;
    }

    public Date getDataChiusura() {
        return dataChiusura;
    }

    public void setDataChiusura(Date dataChiusura) {
        this.dataChiusura = dataChiusura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
