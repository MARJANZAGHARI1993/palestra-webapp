package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.model.Insegnante;
import com.example.palestra_webapp.service.InsegnanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/insegnanti")
public class InsegnantiController {

    @Autowired
    private InsegnanteService insegnanteService;

    @GetMapping
    public String getPage(Model model) {
        List<Insegnante> insegnanti = insegnanteService.elencoInsegnanti();
        model.addAttribute("insegnanti", insegnanti);
        return "insegnanti";
    }
}