package com.example.palestra_webapp.service;

import com.example.palestra_webapp.dao.*;
import com.example.palestra_webapp.model.*;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AbbonamentoServiceImpl implements AbbonamentoService {

    @Autowired
    private IncontroDao incontroDao;
    @Autowired
    private InsegnanteDao insegnanteDao;

    @Autowired
    private DisciplinaDao disciplinaDao;

    @Autowired
    private UtenteDao utenteDao;

    @Autowired
    private AbbonamentoDao abbonamentoDao;

    @Override
    public Abbonamento acquistoAbbonamento(HttpSession session, int idUtente, int idDisciplina, int sedute) {
        try {
            // utente dal database
            Optional<Utente> utenteOptional = utenteDao.findById(idUtente);
            if (utenteOptional.isEmpty()) {
                System.out.println("Utente non trovato");
                return null;
            }
            Utente utente = utenteOptional.get();

            // crea e salva abbonamento
            Abbonamento abbonamento = new Abbonamento();
            abbonamento.setUtente(utente);
            abbonamento.setSedute(sedute);

            // recuperiamo disciplina
            Optional<Disciplina> disciplinaOptional = disciplinaDao.findById(idDisciplina);
            if (disciplinaOptional.isEmpty()) {
                System.out.println("Disciplina non trovata");
                return abbonamento;
            }
            Disciplina disciplina = disciplinaOptional.get();

            List<Insegnante> insegnanti = (List<Insegnante>) insegnanteDao.findAll();
            Insegnante insegnante = new Insegnante();
            for (Insegnante i : insegnanti) {
                if (i.getId() == disciplinaOptional.get().getInsegnante().getId()) {
                    insegnante = i;
                }
            }
            // crea gli incontri per l'abbonamento

            for (int i = 0; i < sedute; i++) {
                Incontro incontro = new Incontro();
                incontro.setDisciplina(disciplina);
                incontro.setInsegnante(insegnante);
                // aggiungi incontro all'abbonamento
                abbonamento.addIncontro(incontro);

                // salva l'incontro
                abbonamento.addIncontro(incontroDao.save(incontro));
            }

            // calcola il costo totale
            double costoTotale = disciplina.getPrezzoUnitario() * sedute;
            abbonamento.setCostoTotale(costoTotale);
            System.out.println("inserimento abbonamento");
            // salva abbonamento
            abbonamentoDao.save(abbonamento);

            return abbonamento;

        } catch (Exception e) {
            System.out.println("Errore nell'acquisto dell'abbonamento: " + e.getMessage());
            return null;
        }
    }


    @Override
    public boolean verificaPagamento(int idAbbonamento) {
        try {
            Optional<Abbonamento> abbonamentoOptional = abbonamentoDao.findById(idAbbonamento);
            if (abbonamentoOptional.isEmpty()) {
                System.out.println("Abbonamento non trovato");
                return false;
            }

            Abbonamento abbonamento = abbonamentoOptional.get();

            // simulazione pagamento
            boolean pagamentoSuccesso = simulaPagamento(abbonamento);

            // aggiorna
            if (pagamentoSuccesso) {
                abbonamento.setStatoPagamento("Pagato");
                abbonamentoDao.save(abbonamento);
                System.out.println("Pagamento verificato con successo");
                return true;
            } else {
                abbonamento.setStatoPagamento("Pagamento fallito");
                abbonamentoDao.save(abbonamento);
                System.out.println("Pagamento fallito");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Errore durante la verifica del pagamento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean simulaPagamento(Abbonamento abbonamento) {
        // sempre riuscito
        return true;
    }

    @Override
    public List<Abbonamento> tuttiAbbonamenti() {
        return (List<Abbonamento>) abbonamentoDao.findAll();
    }


}
