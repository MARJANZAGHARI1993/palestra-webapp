package com.example.palestra_webapp.service;

import jakarta.servlet.http.HttpSession;

public interface AbbonamentoService {

    void acquistoAbbonamento(HttpSession session, int idUtente, int idDisciplina, int sedute);
}
