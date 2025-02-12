package com.example.palestra_webapp.service;

import com.example.palestra_webapp.dao.AbbonamentoDao;
import com.example.palestra_webapp.dao.DisciplinaDao;
import com.example.palestra_webapp.dao.IncontroDao;
import com.example.palestra_webapp.dao.UtenteDao;
import com.example.palestra_webapp.model.Abbonamento;
import com.example.palestra_webapp.model.Disciplina;
import com.example.palestra_webapp.model.Incontro;
import com.example.palestra_webapp.model.Utente;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AbbonamentoServiceImpl implements AbbonamentoService {

    @Autowired
    private IncontroDao incontroDao;

    @Autowired
    private DisciplinaDao disciplinaDao;

    @Autowired
    private UtenteDao utenteDao;

    @Autowired
    private AbbonamentoDao abbonamentoDao;

    @Override
    public void acquistoAbbonamento(HttpSession session, int idUtente, int idDisciplina, int sedute) {
        try {
            // Recupera l'utente dal database
            Optional<Utente> utenteOptional = utenteDao.findById(idUtente);
            if (utenteOptional.isEmpty()) {
                System.out.println("Utente non trovato");
                return;
            }
            Utente utente = utenteOptional.get();

            // Crea e salva l'abbonamento
            Abbonamento abbonamento = new Abbonamento();
            abbonamento.setUtente(utente);
            abbonamento.setSedute(sedute);
            abbonamento.setCostoTotale(0);
            abbonamento = abbonamentoDao.save(abbonamento);

            // Recupera la disciplina
            Optional<Disciplina> disciplinaOptional = disciplinaDao.findById(idDisciplina);
            if (disciplinaOptional.isEmpty()) {
                System.out.println("Disciplina non trovata");
                return;
            }
            Disciplina disciplina = disciplinaOptional.get();

            // Crea incontri e li associa all'abbonamento
            for (int i = 0; i < sedute; i++) {
                Incontro incontro = new Incontro();
                incontro.setDisciplina(disciplina);
                // Se vuoi associare un insegnante, puoi farlo qui

                // Aggiungi incontro all'abbonamento
                abbonamento.addIncontro(incontro);

                // Salva l'incontro
                incontroDao.save(incontro);
            }

            // Calcola il costo totale
            abbonamento.setCostoTotale(disciplina.getPrezzoUnitario() * sedute);
            abbonamentoDao.save(abbonamento);

            // Aggiungi l'abbonamento alla sessione
            session.setAttribute("abbonamento", abbonamento);

        } catch (Exception e) {
            System.out.println("Errore durante l'acquisto dell'abbonamento: " + e.getMessage());
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

            // Simulazione pagamento
            boolean pagamentoSuccesso = simulaPagamento(abbonamento);

            // Aggiorna stato di pagamento
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
        // Simulazione di un pagamento sempre riuscito
        return true;
    }
}
