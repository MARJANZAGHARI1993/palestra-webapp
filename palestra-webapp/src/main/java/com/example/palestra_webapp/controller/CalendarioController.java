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

@Controller
@RequestMapping("/calendario")
public class CalendarioController {

    @Autowired
    private CaldendarioService caldendarioService;

    @Autowired
    private IncontroService incontroService;

    @GetMapping
    public String getPage(@RequestParam int id, Model model) {
        Calendario calendario = caldendarioService.datiCalendario(id);
        model.addAttribute("calendario", calendario);
        return "calendario";
    }

    @GetMapping("/prenota")
    public String prenotaIncontro(@RequestParam("idIncontro") int idIncontro,
                                  @RequestParam("idAbbonamento") int idAbbonamento,
                                  HttpSession session) {
        boolean prenotazioneValida = incontroService.prenotaIncontroManuale(idAbbonamento, idIncontro);

        if (prenotazioneValida) {
            session.setAttribute("messaggio", "Prenotazione avvenuta con successo!");
        } else {
            session.setAttribute("errore", "Errore nella prenotazione, verifica la disponibilità.");
        }

        return "redirect:/calendario";  // Reindirizza alla pagina del calendario
    }

}
