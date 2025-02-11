package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.model.Utente;
import com.example.palestra_webapp.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// localhost:8080/login
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(
            @RequestParam(required = false) String errore,
            Model model,
            HttpSession session) {
        if (session.getAttribute("utente") != null)
            return "redirect:/riservata";
        model.addAttribute("errore", errore);
        model.addAttribute("utente", new Utente()); // Aggiungi l'oggetto Utente al modello
        return "login";
    }


    @PostMapping
    public String loginUtente(@RequestParam String username,
                              @RequestParam String passwordUtente,
                              HttpSession session) {
        if (utenteService.loginUtente(username, passwordUtente, session)) {
            return "redirect:/riservata";  // Redirige alla pagina riservata se il login è successo
        } else {
            return "redirect:/login?errore";  // Redirige alla pagina di login con errore
        }
    }

    @GetMapping("/logout")
    public String logoutUtente(HttpSession session) {
        session.invalidate();
        return "redirect:/login";  // Redirige al login dopo il logout
    }
}


