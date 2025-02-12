package com.example.palestra_webapp.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "insegnanti")
public class Insegnante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nome;

    @Column
    private String cognome;

    @Column(name = "data_nascita")
    private LocalDate dataNascita;

    @Transient // Questo campo non sarà persistito nel database
    private String descrizione;

    @Transient // Questo campo non sarà persistito nel database
    private String fotoUrl;

    @Transient
    private String disciplina;

    // Costruttori
    public Insegnante() {}

    public Insegnante(String nome, String cognome, LocalDate dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public LocalDate getDataNascita() { return dataNascita; }
    public void setDataNascita(LocalDate dataNascita) { this.dataNascita = dataNascita; }
    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }
    public String getDisciplina() {return disciplina;}
    public void setDisciplina(String disciplina) {this.disciplina = disciplina;}
}