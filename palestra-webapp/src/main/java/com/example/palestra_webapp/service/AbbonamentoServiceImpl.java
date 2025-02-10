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

    @Override
    public boolean verificaPagamento(int idAbbonamento) {
        try {
            // Recupera l'abbonamento dal database
            Optional<Abbonamento> abbonamentoOptional = abbonamentoDao.findById(idAbbonamento);
            if (abbonamentoOptional.isEmpty()) {
                System.out.println("Abbonamento non trovato");
                return false;  // Se l'abbonamento non esiste
            }

            Abbonamento abbonamento = abbonamentoOptional.get();

            // Simulazione del processo di verifica pagamento (puoi sostituirlo con una vera API di pagamento)
            boolean pagamentoSuccesso = simulaPagamento(abbonamento);

            // Se il pagamento è andato a buon fine, aggiorna lo stato dell'abbonamento
            if (pagamentoSuccesso) {
                abbonamento.setStatoPagamento("Pagato");  // Aggiorna lo stato come "Pagato"
                abbonamentoDao.save(abbonamento);  // Salva le modifiche nel database
                System.out.println("Pagamento verificato con successo");
                return true;
            } else {
                abbonamento.setStatoPagamento("Pagamento fallito");  // Imposta stato come "fallito"
                abbonamentoDao.save(abbonamento);
                System.out.println("Pagamento fallito");
                return false;
            }

        } catch (Exception e) {
            // Gestione degli errori
            System.out.println("Errore durante la verifica del pagamento: " + e.getMessage());
            return false;  // Ritorna false in caso di errore
        }
    }

    @Override
    public boolean simulaPagamento(Abbonamento abbonamento) {
        return true; 
    }


}




