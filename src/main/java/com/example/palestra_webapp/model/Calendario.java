package com.example.palestra_webapp.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "calendario")
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "data_incontro")
    private LocalDate dataIncontro;

    @Column(name = "ora_inizio_incontro")
    private LocalTime oraInizioIncontro;

    @Column(name = "ora_fine_incontro")
    private LocalTime oraFineIncontro;

    @Column(name = "fk_id_incontri")
    private int fkIdIncontri;

    // Costruttori
    public Calendario() {}

    public Calendario(LocalDate dataIncontro, LocalTime oraInizioIncontro, LocalTime oraFineIncontro, int fkIdIncontri) {
        this.dataIncontro = dataIncontro;
        this.oraInizioIncontro = oraInizioIncontro;
        this.oraFineIncontro = oraFineIncontro;
        this.fkIdIncontri = fkIdIncontri;
    }
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getDataIncontro() { return dataIncontro; }
    public void setDataIncontro(LocalDate dataIncontro) { this.dataIncontro = dataIncontro; }

    public LocalTime getOraInizioIncontro() { return oraInizioIncontro; }
    public void setOraInizioIncontro(LocalTime oraInizioIncontro) { this.oraInizioIncontro = oraInizioIncontro; }

    public LocalTime getOraFineIncontro() { return oraFineIncontro; }
    public void setOraFineIncontro(LocalTime oraFineIncontro) { this.oraFineIncontro = oraFineIncontro; }

    public int getFkIdIncontri() { return fkIdIncontri; }
    public void setFkIdIncontri(int fkIdIncontri) { this.fkIdIncontri = fkIdIncontri; }
}
