package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.model.Utente;
import com.example.palestra_webapp.service.UtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registrazione")
public class RegistrazioneController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(Model model) {
        Utente utente = new Utente();
        model.addAttribute("utente", utente);
        return "registrazione"; // Deve restituire il nome della pagina di registrazione
    }

    @PostMapping
    public String registrazioneUtente(
            @Valid @ModelAttribute("utente") Utente utente,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "registrazione"; // Ritorna alla stessa pagina se ci sono errori
        }

        // Controllo username duplicato
        try {
            if (!utenteService.controlloUsername(utente.getUsername())) {
                model.addAttribute("duplicato", "Username già esistente! Scegline un altro.");
                return "registrazione"; // Ritorna con errore se lo username è duplicato
            }
            // Salvataggio utente
            utenteService.registrazioneUtente(utente);
            return "redirect:/login"; // Reindirizza a /login dopo la registrazione

        } catch (Exception e) {
            model.addAttribute("errore", "Errore durante la registrazione: " + e.getMessage());
            return "registrazione"; // Ritorna con errore se qualcosa va storto
        }
    }

}
