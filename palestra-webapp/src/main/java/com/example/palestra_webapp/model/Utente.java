package com.example.palestra_webapp.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "utenti")
public class Utente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nome;
    @Column
    private String cognome;
    @Column
    private LocalDate dataNascita;
    @Column
    private String indirizzo;
    @Column
    private String mail;
    @Column
    private String telefono;
    @Column
    private String  username ;
    @Column
    private String passwordUtente;
    @Column
    private LocalDate dataRegistrazione;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCognome() {return cognome;}

    public void setCognome(String cognome) {this.cognome = cognome;}

    public LocalDate getDataNascita() {return dataNascita;}

    public void setDataNascita(LocalDate dataNascita) {this.dataNascita = dataNascita;}

    public String getIndirizzo() {return indirizzo;}

    public void setIndirizzo(String indirizzo) {this.indirizzo = indirizzo;}

    public String getMail() {return mail;}

    public void setMail(String mail) {this.mail = mail;}

    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPasswordUtente() {return passwordUtente;}

    public void setPasswordUtente(String passwordUtente) {this.passwordUtente = passwordUtente;}

    public LocalDate getDataRegistrazione() {return dataRegistrazione;}

    public void setDataRegistrazione(LocalDate dataRegistrazione) {this.dataRegistrazione = dataRegistrazione;}
}
