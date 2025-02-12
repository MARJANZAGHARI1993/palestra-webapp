package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// localhost:8080/login
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UtenteService utenteService;

    // GET - Mostra la pagina di login
    @GetMapping
    public String getPage(
            @RequestParam(required = false) String errore,
            Model model,
            HttpSession session) {
        // Se l'utente è già loggato, reindirizza alla pagina riservata
        if (session.getAttribute("utente") != null)
            return "redirect:/riservata";

        // Aggiungi l'errore al modello se presente
        model.addAttribute("errore", errore);
        return "login";
    }

    // POST - Gestisce il login
    @PostMapping
    public String formManager(
            @RequestParam String username,
            @RequestParam String passwordUtente,
            HttpSession session) {
        // Verifica le credenziali dell'utente
        if (!utenteService.loginUtente(username, passwordUtente, session)) {
            return "redirect:/login?errore=1"; // Se il login fallisce, reindirizza con errore
        }
        return "redirect:/riservata"; // Se il login è corretto, reindirizza alla pagina riservata
    }

    // GET - Gestisce il logout
    @GetMapping("/logout")
    public String logoutUtente(HttpSession session) {
        session.invalidate(); // Invalidiamo la sessione dell'utente
        return "redirect:/login"; // Dopo il logout, reindirizza alla pagina di login
    }
}
