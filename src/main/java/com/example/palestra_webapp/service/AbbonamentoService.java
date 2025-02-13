package com.example.palestra_webapp.service;

import com.example.palestra_webapp.model.Abbonamento;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface AbbonamentoService {

    Abbonamento acquistoAbbonamento(HttpSession session, int idUtente, int idDisciplina, int sedute);
    boolean verificaPagamento (int idAbbonamento);
    boolean simulaPagamento(Abbonamento abbonamento);
    // Metodo per ottenere tutti gli abbonamenti di un utente specifico
    List<Abbonamento> tuttiAbbonamenti();
}
