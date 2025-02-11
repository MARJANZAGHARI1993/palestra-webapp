package com.example.palestra_webapp.model;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "calendario")
public class Calendario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDate dataIncontro;

    @Column
    private LocalTime oraInizioIncontro;

    @Column
    private LocalTime oraFineIncontro;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_incontro", referencedColumnName = "id")
    private Incontro incontro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataIncontro() {
        return dataIncontro;
    }

    public void setDataIncontro(LocalDate dataIncontro) {
        this.dataIncontro = dataIncontro;
    }

    public LocalTime getOraInizioIncontro() {
        return oraInizioIncontro;
    }

    public void setOraInizioIncontro(LocalTime oraInizioIncontro) {
        this.oraInizioIncontro = oraInizioIncontro;
    }

    public LocalTime getOraFineIncontro() {
        return oraFineIncontro;
    }

    public void setOraFineIncontro(LocalTime oraFineIncontro) {
        this.oraFineIncontro = oraFineIncontro;
    }

    public Incontro getIncontro() {
        return incontro;
    }

    public void setIncontro(Incontro incontro) {
        this.incontro = incontro;
    }
}
