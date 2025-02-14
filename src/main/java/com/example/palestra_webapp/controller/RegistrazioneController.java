package com.example.palestra_webapp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.palestra_webapp.model.Utente;
import com.example.palestra_webapp.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

@Controller
@RequestMapping("/registrazione")
public class RegistrazioneController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(Model model) {
        Utente utente = new Utente();
        model.addAttribute("utente", utente);
        return "registrazione";
    }

    @PostMapping
    public String registrazioneUtente(
            @RequestParam String nome,
            @RequestParam String cognome,
            @RequestParam String dataNascita,
            @RequestParam String indirizzo,
            @RequestParam String email,
            @RequestParam String telefono,
            @RequestParam String username,
            @RequestParam String passwordUtente,
            @RequestParam(value = "foto", required = false) MultipartFile foto,
            Model model) {


        //Creazione di un nuovo utente
        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setDataNascita(LocalDate.parse(dataNascita));
        utente.setIndirizzo(indirizzo);
        utente.setEmail(email);
        utente.setTelefono(telefono);
        utente.setUsername(username);
        utente.setPasswordUtente(passwordUtente);
        utente.setFoto(String.valueOf(foto));

        try {
            if (!utenteService.controlloUsername(utente.getUsername())) {
                System.out.println("Sono nell'if");
                model.addAttribute("duplicato", "Username già esistente! Scegline un altro.");
                return "registrazione";
            }

            utenteService.registrazioneUtente(utente);
            return "redirect:/login";

        } catch (Exception e) {
            System.out.println("Sono nel catch");
            model.addAttribute("errore", "Errore durante la registrazione: " + e.getMessage());
            return "registrazione";
        }
    }
}