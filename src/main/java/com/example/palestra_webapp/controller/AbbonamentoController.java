package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.model.Abbonamento;
import com.example.palestra_webapp.service.AbbonamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/abbonamenti")
public class AbbonamentoController {

    @Autowired
    private AbbonamentoService abbonamentoService;

    // acquistare un abbonamento
    @PostMapping("/acquista")
    public String acquistoAbbonamento(@RequestParam int idUtente, @RequestParam int idDisciplina, @RequestParam int sedute, HttpSession session, Model model) {

        Abbonamento abbonamento = abbonamentoService.acquistoAbbonamento(session, idUtente, idDisciplina, sedute);
        System.out.print(abbonamento.getCostoTotale());
        // salvare
        session.setAttribute("abbonamento", abbonamento);
        // aggiungo abbonamento
        model.addAttribute("abbonamento", abbonamento);

        return "riservata"; //
    }

    // verificare se il pagamento è stato effettuato
    @PostMapping("/verificaPagamento")
    public String verificaPagamento(@RequestParam int idAbbonamento) {
        boolean risultato = abbonamentoService.verificaPagamento(idAbbonamento);
        return risultato ? "Pagamento verificato con successo" : "Pagamento fallito";
    }

}
