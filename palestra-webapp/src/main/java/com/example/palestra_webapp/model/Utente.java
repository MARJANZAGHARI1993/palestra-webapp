package com.example.palestra_webapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Pattern(regexp = "[a-zA-Z\\sàèìòù']{1,50}", message = "Caratteri Non Ammessi")
    private String nome;
    @Column
    @Pattern(regexp = "[a-zA-Z\\sàèìòù']{1,50}", message = "Caratteri Non Ammessi")
    private String cognome;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Converte correttamente la data
    private LocalDate dataNascita;
    @Column
    private String indirizzo;
    @Column
    private String email;
    @Column
    private String telefono;

    @Lob  // Aggiunto per indicare che la foto sarà un oggetto binario
    private byte[] foto;

    @Column
    private String username;
    @Column
    private String passwordUtente;

    private Date dataRegistrazione;

    // Getter e Setter per 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e Setter per 'nome'
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter per 'cognome'
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    // Getter e Setter per 'dataNascita'
    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    // Getter e Setter per 'indirizzo'
    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    // Getter e Setter per 'email'
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter e Setter per 'telefono'
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Getter e Setter per 'foto' (byte array)
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    // Getter e Setter per 'username'
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter e Setter per 'passwordUtente'
    public String getPasswordUtente() {
        return passwordUtente;
    }

    public void setPasswordUtente(String passwordUtente) {
        this.passwordUtente = passwordUtente;
    }

    // Getter e Setter per 'dataRegistrazione'
    public Date getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(Date dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }
}
