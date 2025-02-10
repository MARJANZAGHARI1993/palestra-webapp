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
            Utente utente = utenteDao.findByUsername(username);
            if (utente != null && utente.getPasswordUtente().equals(password)) { // verifica che la password sia assocciata ad un utente e che non sia null per accedere
                session.setAttribute("utente", utente);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Errore durante il login: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void registrazioneUtente(Utente utente) {
        try {
            utenteDao.save(utente);
        } catch (Exception e) {
            System.out.println("Errore durante la registrazione dell'utente: " + e.getMessage());
        }
    }

    @Override
    public boolean controlloUsername(String username) {
        try {
            return utenteDao.findByUsername(username) == null;
        } catch (Exception e) {
            System.out.println("Errore durante il controllo dell'username: " + e.getMessage());
            return false;
        }
    }
}
