package com.example.palestra_webapp.service;

import com.example.palestra_webapp.model.Abbonamento;
import jakarta.servlet.http.HttpSession;

public interface AbbonamentoService {

    void acquistoAbbonamento(HttpSession session, int idUtente, int idDisciplina, int sedute);
    boolean verificaPagamento (int idAbbonamento);
    boolean simulaPagamento(Abbonamento abbonamento);
}
