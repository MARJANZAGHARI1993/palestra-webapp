package com.example.palestra_webapp.service;

import com.example.palestra_webapp.model.Incontro;

import java.util.List;
import java.util.Optional;

public interface IncontroService {
    Incontro aggiungiIncontro(Incontro incontro);
    Optional<Incontro> getIncontroById(int id);
    List<Incontro> elencoIncontri();
    Incontro aggiornaIncontro(int id, Incontro incontro);
    void eliminaIncontro(int id);
}
