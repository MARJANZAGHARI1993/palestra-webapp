package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.model.Abbonamento;
import com.example.palestra_webapp.model.Calendario;
import com.example.palestra_webapp.model.Incontro;
import com.example.palestra_webapp.model.Utente;
import com.example.palestra_webapp.service.AbbonamentoService;
import com.example.palestra_webapp.service.CaldendarioService;
import com.example.palestra_webapp.service.IncontroService;
import com.example.palestra_webapp.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private CaldendarioService caldendarioService;

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

        // Recupera i calendari
        List<Calendario> calendarioList = caldendarioService.elencoCalendario();

        // Aggiungi i dati al modello per la vista
        model.addAttribute("utente", utente);
        model.addAttribute("incontri", incontri);
        model.addAttribute("ultimoAbbonamento", ultimoAbbonamento.orElse(null)); // se non c'è passa null
        model.addAttribute("altriAbbonamenti", altriAbbonamenti); // agguinge altri abbonamenti
        model.addAttribute("calendarioList", calendarioList); // aggiunge il calendario
        model.addAttribute("send", send);

        // Ritorna la vista "riservata"
        return "riservata";
    }

    @GetMapping("/logout")
    public String logoutUtente(HttpSession session){
        // Rimuove l'utente dalla sessione
        session.removeAttribute("utente");
        return "redirect:/login";
    }

    @GetMapping("/rimuovi")
    public String eliminaIncontro(@RequestParam int id, HttpSession session) {
        // elimina incontro con id
        incontroService.eliminaIncontro(id);
        return "redirect:/riservata"; // torna alla pagina
    }

    @PostMapping
    public String formManager(
            @Valid @ModelAttribute Utente utente,
            @RequestParam(value = "foto", required = false) MultipartFile foto,
            BindingResult result,
            HttpSession session) {

        if (result.hasErrors()) {
            return "riservata";
        }

        // Passa utente e foto al servizio per la registrazione
        utenteService.registrazioneUtente(utente, foto);

        // Salva l'utente nella sessione
        session.setAttribute("utente", utente);

        // Redirige alla pagina riservata
        return "redirect:/riservata";
    }


    @PostMapping("/acquista")
    public String acquistaAbbonamento(@RequestParam int idDisciplina,
                                      @RequestParam int sedute,
                                      HttpSession session,
                                      Model model) {

        Utente utenteSessione = (Utente) session.getAttribute("utente");
        if (utenteSessione == null) {
            return "redirect:/login";
        }


        Abbonamento abbonamento = abbonamentoService.acquistoAbbonamento(session, utenteSessione.getId(), idDisciplina, sedute);


        model.addAttribute("abbonamento", abbonamento);
        return "redirect:/riservata";
    }


}
