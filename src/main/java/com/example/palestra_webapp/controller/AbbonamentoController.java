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

    // Endpoint per visualizzare gli altri abbonamenti (da Thymeleaf)
    @GetMapping("/altri")
    public String getAltriAbbonamenti(Model model) {
        List<Abbonamento> altriAbbonamenti = abbonamentoService.tuttiAbbonamenti();
        model.addAttribute("altriAbbonamenti", altriAbbonamenti);
        return "altriAbbonamenti";  // Nome del template Thymeleaf
    }
}
