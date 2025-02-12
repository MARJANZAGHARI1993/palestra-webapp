package com.example.palestra_webapp.controller;

import com.example.palestra_webapp.model.Calendario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chisiamo")
public class ChiSiamoController {

    @GetMapping
    public String getPage(Model model) {
        return "chisiamo";
    }


}
