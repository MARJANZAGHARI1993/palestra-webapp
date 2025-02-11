package com.example.palestra_webapp.controller;
import com.example.palestra_webapp.model.Disciplina;
import com.example.palestra_webapp.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/discipline")
public class DisciplineController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public String getPage(@RequestParam int id, Model model) {
        Disciplina disciplina = disciplinaService.datiDisciplina(id);
        model.addAttribute("disciplina", disciplina);
        return "disciplina";
    }
}
