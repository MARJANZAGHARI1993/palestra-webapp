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

    // Relazione ManyToMany tra Abbonamento e Incontro
    @ManyToMany
    @JoinTable(
            name = "abbonamenti_incontri", // Tabella di abbinamento
            joinColumns = @JoinColumn(name = "id_abbonamento", referencedColumnName = "id"), // Chiave esterna per Abbonamento
            inverseJoinColumns = @JoinColumn(name = "fk_id_incontro", referencedColumnName = "id") // Chiave esterna per Incontro
    )
    private List<Incontro> incontri = new ArrayList<>();


    @Column
    private int sedute;

    @Column
    private String statoPagamento;

    @Column
    private double costoTotale;

    // Getter e Setter

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

    public List<Incontro> getIncontri() {
        return incontri;
    }

    public void setIncontri(List<Incontro> incontri) {
        this.incontri = incontri;
    }

    public void addIncontro(Incontro incontro) {
        this.incontri.add(incontro);
    }

    public void removeIncontro(Incontro incontro) {
        this.incontri.remove(incontro);
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
