package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.model.Disciplina;
import com.example.palestra_webapp.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/discipline")
public class DisciplineController {

    @Autowired
    private DisciplinaService disciplinaService;

    // Rimosso il metodo getAllDiscipline

    @GetMapping("/{nome}")
    public String getDisciplina(@PathVariable String nome, Model model) {
        Optional<Disciplina> disciplina = disciplinaService.getDisciplinaByNome(nome);
        if (disciplina.isEmpty()) {
            return "redirect:/"; // Gestisci l'errore se la disciplina non viene trovata
        }
        model.addAttribute("disciplina", disciplina.get());
        return nome.toLowerCase(); // Restituisce il nome della pagina HTML (es. "yoga")
    }
}