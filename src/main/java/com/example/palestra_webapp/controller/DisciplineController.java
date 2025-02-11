package com.example.palestra_webapp.controller;
import com.example.palestra_webapp.model.Disciplina;
import com.example.palestra_webapp.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/discipline")
public class DisciplineController {

        @Autowired
        private DisciplinaService disciplinaService;

    @GetMapping
    public String getPage(@RequestParam(required = false) Integer id, Model model) {
        if (id != null) {
            Disciplina disciplina = disciplinaService.datiDisciplina(id);
            if (disciplina == null) {
                return "redirect:/errore"; // Se l'ID non è valido, reindirizza a una pagina di errore
            }
            model.addAttribute("disciplina", disciplina);
            return "disciplina"; // Mostra la pagina con i dettagli della disciplina
        }

        // Se l'ID è nullo, restituisci la lista di tutte le discipline
        List<Disciplina> discipline = disciplinaService.elencoDiscipline();
        model.addAttribute("discipline", discipline);
        return "discipline"; // Mostra la pagina con tutte le discipline
    }

}



