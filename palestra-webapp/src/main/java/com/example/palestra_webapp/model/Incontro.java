package com.example.palestra_webapp.model;

import jakarta.persistence.*;

public class Incontro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.REFRESH) // molti a uno, un incontro è associato a una sola disciplina, ma una disciplina può essere collegata a più incontri. REFRESH: possiamo aggiornare la disciplina se è cambiata nel database, ma senza eliminarla o modificarla automaticamente.
    @JoinColumn(name = "fk_id_disciplina", referencedColumnName = "id")
    private Disciplina disciplina;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fk_id_insegnante", referencedColumnName = "id")
    private Insegnante insegnante;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Insegnante getInsegnante() {
        return insegnante;
    }

    public void setInsegnante(Insegnante insegnante) {
        this.insegnante = insegnante;
    }
}
