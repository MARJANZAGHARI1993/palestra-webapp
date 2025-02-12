package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.service.AbbonamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/abbonamenti")  // Questo definisce il percorso base per tutte le operazioni sugli abbonamenti
public class AbbonamentoController {

    @Autowired
    private AbbonamentoService abbonamentoService;

    // Metodo per acquistare un abbonamento
    @PostMapping("/acquista")
    public String acquistoAbbonamento(@RequestParam int idUtente, @RequestParam int idDisciplina, @RequestParam int sedute, HttpSession session) {
        abbonamentoService.acquistoAbbonamento(session, idUtente, idDisciplina, sedute);
        return "Abbonamento acquistato con successo!";
    }

    // Metodo per verificare se il pagamento è stato effettuato
    @PostMapping("/verificaPagamento")
    public String verificaPagamento(@RequestParam int idAbbonamento) {
        boolean risultato = abbonamentoService.verificaPagamento(idAbbonamento);
        return risultato ? "Pagamento verificato con successo" : "Pagamento fallito";
    }
}
