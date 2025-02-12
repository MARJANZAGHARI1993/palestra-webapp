package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.model.Abbonamento;
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
import java.util.Optional;

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
        // controllo utente loggato
        Utente utenteSessione = (Utente) session.getAttribute("utente");

        // no loggato, pagina login
        if (utenteSessione == null) {
            return "redirect:/login";
        }

        // recupera i dati utente dalla sessione
        Utente utente = utenteService.datiUtente(utenteSessione.getId());

        // ultimo abobnamento utente
        Optional<Abbonamento> ultimoAbbonamento = utente.getUltimoAbbonamento();

        // tutti abbonamenti dell'utente
        List<Abbonamento> altriAbbonamenti = abbonamentoService.tuttiAbbonamenti();

        // incontri disponibili
        List<Incontro> incontri = incontroService.elencoIncontri();

        model.addAttribute("utente", utente);
        model.addAttribute("incontri", incontri);
        model.addAttribute("ultimoAbbonamento", ultimoAbbonamento.orElse(null)); // se non c'è, passa null
        model.addAttribute("altriAbbonamenti", altriAbbonamenti);
        model.addAttribute("send", send);

        return "riservata";
    }

    @GetMapping("/logout")
    public String logoutUtente(HttpSession session){
        // rimuove l'utente dalla sessione
        session.removeAttribute("utente");
        return "redirect:/";
    }

    @GetMapping("/rimuovi")
    public String eliminaIncontro(@RequestParam int id, HttpSession session) {

        incontroService.eliminaIncontro(id);
        return "redirect:/riservata";
    }

    @PostMapping
    public String formManager(
            @Valid @ModelAttribute Utente utente,
            BindingResult result,
            HttpSession session) {
        if (result.hasErrors()) {
            return "riservata";
        }

        // registra utente
        utenteService.registrazioneUtente(utente);

        // salva utente sessione
        session.setAttribute("utente", utente);

        return "redirect:/riservata";
    }
}
