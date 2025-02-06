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
    public boolean loginUtente(String username, String password,
                               HttpSession session) {
        Utente utente = utenteDao.findByProfiloUsernameAndProfiloPassword(username,password);
        if(utente != null){
            session.setAttribute("utente",utente);
            return true;
        }
        return false;
    }

    @Override
    public void registrazioneUtente(Utente utente) {
        utenteDao.save(utente);
    }

    @Override
    public boolean controlloUsername(String username) {
        return utenteDao.findByProfiloUsername(username) == null;
    }
}
