package com.example.palestra_webapp.service;

import com.example.palestra_webapp.dao.UtenteDao;
import com.example.palestra_webapp.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteDao utenteDao;

    @Override
    public boolean loginUtente(String username, String password, HttpSession session) {
        Utente utente = utenteDao.findByUsername(username);
        if (utente != null && utente.getPasswordUtente().equals(password)) {
            session.setAttribute("utente", utente);
            return true;
        }
        return false;
    }

    @Override
    public void registrazioneUtente(Utente utente) {
        utente.setDataRegistrazione(LocalDate.now()); //aggiunto data registrazione
        utenteDao.save(utente);
    }

    @Override
    public boolean controlloUsername(String username) {
        return utenteDao.findByUsername(username) == null;
    }

    @Override
    public Utente datiUtente(int id) {
        return utenteDao.findById(id).get();
    }
}