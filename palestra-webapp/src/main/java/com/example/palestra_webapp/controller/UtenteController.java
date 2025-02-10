package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.model.Utente;
import com.example.palestra_webapp.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/registrazione")
    public String registrazioneUtente(@RequestBody Utente utente) {
        try {
            if (!utenteService.controlloUsername(utente.getUsername())) {
                return "Username già esistente!";
            }
            utenteService.registrazioneUtente(utente);
            return "Registrazione avvenuta con successo!";
        } catch (Exception e) {
            return "Errore nella registrazione: " + e.getMessage();
        }
    }
    @GetMapping
    public String getPage(Model model) {
        Utente utente = new Utente();
        model.addAttribute("utente", utente);
        return "registrazione";
    }

    @PostMapping("/login")
    public String loginUtente(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session) {
        if (utenteService.loginUtente(username, password, session)) {
            return "Login effettuato con successo!";
        } else {
            return "Credenziali errate!";
        }
    }

    @GetMapping("/logout")
    public String logoutUtente(HttpSession session) {
        session.invalidate();
        return "Logout effettuato!";
    }
}
