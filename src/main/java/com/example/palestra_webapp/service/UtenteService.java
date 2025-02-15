package com.example.palestra_webapp.service;
import com.example.palestra_webapp.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

public interface UtenteService {
    boolean loginUtente(String username, String password, HttpSession session);
    void registrazioneUtente(Utente utente, MultipartFile foto);

    boolean controlloUsername(String username);
    Utente datiUtente(int id);
}
