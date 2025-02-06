package com.example.palestra_webapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fk_id_utente", referencedColumnName = "id")
    private Utente utente;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fk_id_incontro", referencedColumnName = "id")
    private Incontro incontro;

    @Column
    private int sedute;

    @Column
    private String statoPagamento;

    @Column
    private double costoTotale;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Incontro getIncontro() {
        return incontro;
    }

    public void setIncontro(Incontro incontro) {
        this.incontro = incontro;
    }

    public int getSedute() {
        return sedute;
    }

    public void setSedute(int sedute) {
        this.sedute = sedute;
    }

    public String getStatoPagamento() {
        return statoPagamento;
    }

    public void setStatoPagamento(String statoPagamento) {
        this.statoPagamento = statoPagamento;
    }

    public double getCostoTotale() {
        return costoTotale;
    }

    public void setCostoTotale(double costoTotale) {
        this.costoTotale = costoTotale;
    }
}