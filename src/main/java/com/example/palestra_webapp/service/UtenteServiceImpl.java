package com.example.palestra_webapp.service;

import com.example.palestra_webapp.dao.UtenteDao;
import com.example.palestra_webapp.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Base64;

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
    public void registrazioneUtente(Utente utente, MultipartFile foto) {
        utente.setDataRegistrazione(LocalDate.now());

        // Gestione della foto (se presente)
        if (foto != null && !foto.isEmpty()) {
            try {
                String formato = foto.getContentType();
                String fotoCodificata = "data:" + formato + ";base64," + Base64.getEncoder().encodeToString(foto.getBytes());
                utente.setFoto(fotoCodificata);  // Imposta la foto codificata in Base64
            } catch (Exception e) {
                System.out.println("Errore nella codifica della foto: " + e.getMessage());
            }
        }

        // Salva l'utente nel database
        utenteDao.save(utente);
    }


    @Override
    public boolean controlloUsername(String username) {
        return utenteDao.findByUsername(username) == null;
    }

    @Override
    public Utente datiUtente(int id) {
        return utenteDao.findById(id).orElse(null);
    }
}