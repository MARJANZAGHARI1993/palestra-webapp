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
            // controllo se l'utente esiste
            Optional<Utente> utenteOptional = utenteDao.findById(idUtente);
            if(utenteOptional.isEmpty()) {
                System.out.println("Utente non trovato");
                return; // se non trova l'utente esce
            }
            Utente utente = utenteOptional.get();

            // controllo se la disciplina esiste
            Optional<Disciplina> disciplinaOptional = disciplinaDao.findById(idDisciplina);
            if(disciplinaOptional.isEmpty()) {
                System.out.println("Disciplina non trovata");
                return; // si esce se non viene trovata alcuna disciplina
            }

            Disciplina disciplina = disciplinaOptional.get();

            double costoTotale = disciplina.getPrezzoUnitario() * sedute;

            // creiamo l'oggetto abbonamento e settiamo i dettagli
            Abbonamento abbonamento = new Abbonamento();
            abbonamento.setUtente(utente);
            abbonamento.setDisciplina(disciplina);
            abbonamento.setSedute(sedute);
            abbonamento.setCostoTotale(costoTotale);

            // salva l'abbonamento nel db
            abbonamento = abbonamentoDao.save(abbonamento);

            // aggiunge l'abbonamento alla sessione
            session.setAttribute("abbonamento", abbonamento);

        } catch (Exception e) {
            System.out.println("Si è verificato un errore durante l'acquisto dell'abbonamento: " + e.getMessage());

        }
    }

    @Override
    public boolean verificaPagamento(int idAbbonamento) {
        try {
            // recupera i dati dell'abbonamento dal db
            Optional<Abbonamento> abbonamentoOptional = abbonamentoDao.findById(idAbbonamento);
            if (abbonamentoOptional.isEmpty()) { // se non è presente alcun abbonamento
                System.out.println("Abbonamento non trovato");
                return false;
            }

            Abbonamento abbonamento = abbonamentoOptional.get();

            // simulare un vero pagamento
            boolean pagamentoSuccesso = simulaPagamento(abbonamento);

            // se è andato a buon fine
            if (pagamentoSuccesso) {
                abbonamento.setStatoPagamento("Pagato");  // aggiorna con "pagato"
                abbonamentoDao.save(abbonamento);  // salva nel db
                System.out.println("Pagamento verificato con successo");
                return true;
            } else {
                abbonamento.setStatoPagamento("Pagamento fallito");  // imposta stato "fallito"
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
        return true; 
    }


}




