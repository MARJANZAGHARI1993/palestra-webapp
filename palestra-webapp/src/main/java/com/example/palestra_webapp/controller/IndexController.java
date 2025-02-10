package com.example.palestra_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


// localhost:8080 (sostituito da libreria.it)
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String getPage(Model model) {
        return "index";
    }
}
