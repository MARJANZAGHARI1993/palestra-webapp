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
        // Controllo se l'utente è loggato
        Utente utenteSessione = (Utente) session.getAttribute("utente");

        // Se l'utente non è loggato, redirige alla pagina di login
        if (utenteSessione == null) {
            return "redirect:/login";
        }

        // Recupera i dati dell'utente dalla sessione
        Utente utente = utenteService.datiUtente(utenteSessione.getId());

        // Ottieni l'ultimo abbonamento dell'utente
        Optional<Abbonamento> ultimoAbbonamento = utente.getUltimoAbbonamento();

        // Recupera tutti gli abbonamenti dell'utente
        List<Abbonamento> altriAbbonamenti = abbonamentoService.tuttiAbbonamenti();

        // Recupera gli incontri disponibili
        List<Incontro> incontri = incontroService.elencoIncontri();

        // Aggiungi i dati al modello per la vista
        model.addAttribute("utente", utente);
        model.addAttribute("incontri", incontri);
        model.addAttribute("ultimoAbbonamento", ultimoAbbonamento.orElse(null)); // Se non c'è, passa null
        model.addAttribute("altriAbbonamenti", altriAbbonamenti); // Aggiungi gli altri abbonamenti
        model.addAttribute("send", send);

        return "riservata";
    }

    @GetMapping("/logout")
    public String logoutUtente(HttpSession session){
        // Rimuove l'utente dalla sessione
        session.removeAttribute("utente");
        return "redirect:/login"; // Redirige alla home o pagina iniziale
    }

    @GetMapping("/rimuovi")
    public String eliminaIncontro(@RequestParam int id, HttpSession session) {
        // Elimina l'incontro con l'ID fornito
        incontroService.eliminaIncontro(id);
        return "redirect:/riservata"; // Ritorna alla pagina riservata
    }

    @PostMapping
    public String formManager(
            @Valid @ModelAttribute Utente utente,
            BindingResult result,
            HttpSession session) {
        if (result.hasErrors()) {
            return "riservata";
        }

        // Registra o aggiorna l'utente
        utenteService.registrazioneUtente(utente);

        // Salva l'utente aggiornato nella sessione
        session.setAttribute("utente", utente);

        // Redirige alla pagina riservata
        return "redirect:/riservata";
    }
}
