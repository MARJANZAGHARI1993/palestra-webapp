package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.model.Calendario;
import com.example.palestra_webapp.service.CaldendarioService;
import com.example.palestra_webapp.service.IncontroService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/calendario")
public class CalendarioController {

    @Autowired
    private CaldendarioService caldendarioService;

    @Autowired
    private IncontroService incontroService;


    @GetMapping
    public String getPage(Model model) {

        List<Calendario> calendarioList = caldendarioService.elencoCalendario();


        model.addAttribute("calendarioList", calendarioList);

        return "calendario";
    }

    // prenotare
    @GetMapping("/prenota")
    public String prenotaIncontro(@RequestParam("idIncontro") int idIncontro,
                                  @RequestParam("idAbbonamento") int idAbbonamento,
                                  HttpSession session) {
        // prenotare
        boolean prenotazioneValida = incontroService.prenotaIncontroManuale(idAbbonamento, idIncontro);

        // messaggio
        if (prenotazioneValida) {
            session.setAttribute("messaggio", "Prenotazione avvenuta con successo!");
        } else {
            session.setAttribute("errore", "Errore nella prenotazione, verifica la disponibilità.");
        }


        return "redirect:/calendario";
    }


}
