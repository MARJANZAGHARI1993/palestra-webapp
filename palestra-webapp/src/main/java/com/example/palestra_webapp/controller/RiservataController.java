package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.model.Incontro;
import com.example.palestra_webapp.model.Utente;
import com.example.palestra_webapp.service.AbbonamentoService;
import com.example.palestra_webapp.service.IncontroService;
import com.example.palestra_webapp.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// localhost:8080/riservata
@Controller
@RequestMapping("/riservata")
public class RiservataController {

    @Autowired
    private AbbonamentoService abbonamentoService;

    @Autowired
    private IncontroService incontroService;

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(HttpSession session, Model model, @RequestParam(required = false) String send) {
        if (session.getAttribute("utente") == null) {
            return "redirect:/login";
        }
        Utente utente = (Utente) session.getAttribute("utente");
        List<Incontro> incontri = incontroService.elencoIncontri();  // Recupera gli abbonamenti e gli incontri disponibili
        model.addAttribute("utente", utente);
        model.addAttribute("incontri", incontri);
        model.addAttribute("send", send);
        return "riservata";
    }

    @GetMapping("/logout")
    public String logoutUtente(HttpSession session){
        session.removeAttribute("utente");
        return "redirect:/";
    }

    @GetMapping("/rimuovi")
    public String eliminaIncontro(@RequestParam int id, HttpSession session) {
        incontroService.eliminaIncontro(id);  // Usa l'istanza del servizio
        return "redirect:/riservata";
    }

    @PostMapping
    public String formManager(
            @Valid @ModelAttribute Utente utente,
            BindingResult result,
            HttpSession session) {
        if(result.hasErrors())
            return "riservata";
        utenteService.registrazioneUtente(utente);
        session.setAttribute("utente", utente);
        return "redirect:/riservata";
    }
}
