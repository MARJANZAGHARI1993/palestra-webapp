package com.example.palestra_webapp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/insegnanti")
public class InsegnantiController {

    @GetMapping
    public String getPage(Model model) {
        return "insegnanti";
    }
}


