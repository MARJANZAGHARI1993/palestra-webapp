package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.model.Utente;
import com.example.palestra_webapp.service.UtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/registrazione")
public class RegistrazioneController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(Model model) {
        Utente utente = new Utente();
        model.addAttribute("utente", utente);
        return "registrazione"; // Ritorna la pagina di registrazione
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String registrazioneUtente(@Valid @ModelAttribute("utente") Utente utente,
                                      BindingResult result,
                                      @RequestParam(value = "foto", required = false) MultipartFile foto,  // Foto opzionale
                                      Model model) {
        if (result.hasErrors()) {
            return "registrazione"; // Ritorna alla pagina di registrazione in caso di errore
        }

        try {
            // Verifica se lo username è già esistente
            if (!utenteService.controlloUsername(utente.getUsername())) {
                model.addAttribute("duplicato", "Username già esistente! Scegline un altro.");
                return "registrazione";
            }

            // Gestione del file foto (se presente)
            if (foto != null && !foto.isEmpty()) {
                byte[] fotoBytes = foto.getBytes();
                utente.setFoto(fotoBytes); // Salva il byte array della foto nel modello
            }

            // Procedi con la registrazione dell'utente
            utenteService.registrazioneUtente(utente);

            return "redirect:/riservata"; // Redirect alla pagina riservata
        } catch (IOException e) {
            model.addAttribute("errore", "Errore durante il caricamento della foto: " + e.getMessage());
            return "registrazione";
        }
    }
}
