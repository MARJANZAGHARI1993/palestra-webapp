package com.example.palestra_webapp.controller;
import com.example.palestra_webapp.model.Calendario;
import com.example.palestra_webapp.service.CaldendarioService;
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

    @GetMapping
    public String getPage(@RequestParam int id, Model model) {
        Calendario calendario = caldendarioService.datiCalendario(id);
        model.addAttribute("calendario", calendario);
        return "calendario";
    }
}
