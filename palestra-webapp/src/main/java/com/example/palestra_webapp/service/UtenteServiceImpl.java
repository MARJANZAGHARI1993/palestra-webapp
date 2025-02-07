package com.example.palestra_webapp.service;

import com.example.palestra_webapp.dao.UtenteDao;
import com.example.palestra_webapp.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteDao utenteDao;

    @Override
    public boolean loginUtente(String username, String password, HttpSession session) {
        try {
            Utente utente = utenteDao.findByUsernameAndPassword(username, password);
            if (utente != null) {
                session.setAttribute("utente", utente);
                return true;
            }
        } catch (Exception e) {
            System.err.println("Errore durante il login: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void registrazioneUtente(Utente utente) {
        try {
            utenteDao.save(utente);
        } catch (Exception e) {
            System.err.println("Errore durante la registrazione dell'utente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean controlloUsername(String username) {
        try {
            return utenteDao.findByUsername(username) == null;
        } catch (Exception e) {
            System.err.println("Errore durante il controllo dell'username: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
