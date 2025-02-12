package com.example.palestra_webapp.controller;
import com.example.palestra_webapp.model.Disciplina;
import com.example.palestra_webapp.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/discipline")
public class DisciplineController {

    @Autowired
    private DisciplinaService disciplinaService;

    // Mostra la lista di tutte le discipline
    @GetMapping
    public String getAllDiscipline(Model model) {
        List<Disciplina> discipline = disciplinaService.elencoDiscipline();
        model.addAttribute("discipline", discipline);
        return "discipline"; // Pagina con l'elenco delle discipline
    }

    @GetMapping("/{nome}")
    public String getDisciplina(@PathVariable String nome, Model model) {
        Optional<Disciplina> disciplina = disciplinaService.getDisciplinaByNome(nome);
        if (disciplina.isEmpty()) {
            return "redirect:/errore";
        }
        model.addAttribute("disciplina", disciplina.get());
        return nome.toLowerCase();
    }

}




