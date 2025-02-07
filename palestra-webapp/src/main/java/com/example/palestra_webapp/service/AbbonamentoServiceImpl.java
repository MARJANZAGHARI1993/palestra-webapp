package com.example.palestra_webapp.service;
import com.example.palestra_webapp.dao.AbbonamentoDao;
import com.example.palestra_webapp.dao.DisciplinaDao;
import com.example.palestra_webapp.dao.UtenteDao;
import com.example.palestra_webapp.model.Abbonamento;
import com.example.palestra_webapp.model.Disciplina;
import com.example.palestra_webapp.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbbonamentoServiceImpl implements AbbonamentoService {

    @Autowired
    private DisciplinaDao disciplinaDao;

    @Autowired
    private UtenteDao utenteDao;

    @Autowired
    private AbbonamentoDao abbonamentoDao;

    @Override
    public void acquistoAbbonamento(HttpSession session, int idUtente, int idDisciplina, int sedute) {
        try {
            // Verifica se l'utente esiste
            Optional<Utente> utenteOptional = utenteDao.findById(idUtente);
            if(utenteOptional.isEmpty()) {
                System.out.println("Utente non trovato");
                return; // Esci dal metodo se l'utente non viene trovato
            }
            Utente utente = utenteOptional.get();

            // Verifica se la disciplina esiste
            Optional<Disciplina> disciplinaOptional = disciplinaDao.findById(idDisciplina);
            if(disciplinaOptional.isEmpty()) {
                System.out.println("Disciplina non trovata");
                return; // Esci dal metodo se la disciplina non viene trovata
            }

            Disciplina disciplina = disciplinaOptional.get();

            // Calcolo del costo totale
            double costoTotale = disciplina.getPrezzoUnitario() * sedute;

            // Creazione dell'oggetto Abbonamento
            Abbonamento abbonamento = new Abbonamento();
            abbonamento.setUtente(utente);
            abbonamento.setDisciplina(disciplina);
            abbonamento.setSedute(sedute);
            abbonamento.setCostoTotale(costoTotale);

            // Salvataggio dell'abbonamento nel database
            abbonamento = abbonamentoDao.save(abbonamento);

            // Aggiungi l'abbonamento alla sessione
            session.setAttribute("abbonamento", abbonamento);

        } catch (Exception e) {
            // Gestione degli errori
            System.out.println("Si è verificato un errore durante l'acquisto dell'abbonamento: " + e.getMessage());
            
        }
    }


}




