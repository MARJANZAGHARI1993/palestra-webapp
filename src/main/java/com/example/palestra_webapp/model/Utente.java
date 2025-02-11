package com.example.palestra_webapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

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
    private String email;
    @Column
    private String telefono;
    @Column(unique = true)
    private String  username ;
    @Column
    private String passwordUtente;
    @Column
    private LocalDate dataRegistrazione;
    @Column(name = "foto")
    private String foto;

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

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPasswordUtente() {return passwordUtente;}

    public void setPasswordUtente(String passwordUtente) {this.passwordUtente = passwordUtente;}

    public LocalDate getDataRegistrazione() {return dataRegistrazione;}

    public void setDataRegistrazione(LocalDate dataRegistrazione) {this.dataRegistrazione = dataRegistrazione;}

    public String getFoto() {return foto;}

    public void setFoto(String foto) {this.foto = foto;}
}
