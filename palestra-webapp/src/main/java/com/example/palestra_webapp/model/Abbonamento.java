package com.example.palestra_webapp.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fk_id_utente", referencedColumnName = "id")
    private Utente utente;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fk_id_incontro", referencedColumnName = "id")
    private Incontro incontro;

    @ManyToMany
    @JoinTable(
            name = "abbonamenti_incontri",  // nome della tabella di join, puoi modificarlo come preferisci
            joinColumns = @JoinColumn(name = "id_abbonamento", referencedColumnName = "id"),  // colonna che fa riferimento a Abbonamento
            inverseJoinColumns = @JoinColumn(name = "fk_id_incontro", referencedColumnName = "id"))
    // colonna che fa riferimento a Incontro
    private List<Incontro> incontri = new ArrayList<>();

    @Column
    private int sedute;

    @Column
    private String statoPagamento;

    @Column
    private double costoTotale;

    Disciplina disciplina;

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

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

    public List<Incontro> getIncontri() {
        return incontri;
    }
}