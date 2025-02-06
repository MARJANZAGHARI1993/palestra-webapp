package com.example.palestra_webapp.service;

import com.example.palestra_webapp.model.Utente;
import jakarta.servlet.http.HttpSession;


public interface UtenteService {
boolean loginUtente(String username, String password, HttpSession session);
void registrazioneUtente(Utente utente);
boolean controlloUsername(String username);

}
