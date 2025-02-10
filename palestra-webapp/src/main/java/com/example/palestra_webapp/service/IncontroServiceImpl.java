package com.example.palestra_webapp.service;

import com.example.palestra_webapp.dao.AbbonamentoDao;
import com.example.palestra_webapp.dao.IncontroDao;
import com.example.palestra_webapp.model.Abbonamento;
import com.example.palestra_webapp.model.Incontro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncontroServiceImpl implements IncontroService {

    @Autowired
    private IncontroDao incontroDao;

    @Autowired
    private AbbonamentoDao abbonamentoDao;

    @Override
    public boolean prenotaIncontroManuale(int idAbbonamento, int id) {
        try {
            Optional<Abbonamento> abbonamentoOptional = abbonamentoDao.findById(idAbbonamento);
            if (abbonamentoOptional.isEmpty()) {
                System.out.println("Non è presente nessun abbonamento");
                return false;
            }

            Abbonamento abbonamento = abbonamentoOptional.get(); // dati dell'abbonamento
            // controllo se ci sono sedute ancora disponibili
            if (abbonamento.getSedute() <= 0) {
                System.out.println("Non ci sono più sedute disponibili per questo abbonamento");
                return false;
            }

            // trovare l'incontro
            Optional<Incontro> incontroOptional = incontroDao.findById(id);
            if (incontroOptional.isEmpty()) {
                System.out.println("Nessun incontro trovato");
                return false;
            }

            Incontro incontro = incontroOptional.get();

            // controllo incontro già prenotato
            if (incontro.getAbbonamenti().contains(abbonamento)) {
                System.out.println("Hai già prenotato questo incontro");
                return false;
            }

            // aggiunge l'incontro all'abbonamento
            incontro.getAbbonamenti().add(abbonamento);
            abbonamento.getIncontri().add(incontro);

            // scalare il num sedute da quelle disponibili
            abbonamento.setSedute(abbonamento.getSedute() - 1);

            // salva nel db
            incontroDao.save(incontro);
            abbonamentoDao.save(abbonamento);

            System.out.println("Incontro prenotato con successo");
            return true;
        } catch (Exception e){
            System.out.println("C'è stato un errore durante la prenotazione dell'incontro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Incontro> getIncontroById(int id) {
        try {
            Optional<Incontro> incontroOptional = incontroDao.findById(id);
            if (incontroOptional.isPresent()) {
                return Optional.of(incontroOptional.get());  // restituisce l'incontro trovato
            } else {
                System.out.println("Non abbiamo trovato nessun incontro con l'ID: " + id);
                return Optional.empty();
            }
        } catch (Exception e) {
            System.out.println("C'è stato un errore: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Incontro> elencoIncontri() {
        try {
            return (List<Incontro>) incontroDao.findAll();
        } catch (Exception e) {
            System.out.println("C'è stato un errore: " + e.getMessage());
            return List.of();
        }
    }

    public Incontro aggiornaIncontro(int id, Incontro incontro) {
        try {
            Optional<Incontro> incontroTrovato = incontroDao.findById(id);
            if (incontroTrovato.isPresent()) {
                incontro.setId(id);  // l'ID viene mantenuto
                return incontroDao.save(incontro);  // salviamo l'incontro aggiornato
            } else {
                System.out.println("Incontro non trovato con l'ID: " + id);
                return null;
            }
        } catch (Exception e) {
            System.out.println("C'è stato un errore: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void eliminaIncontro(int id) {
        Optional<Incontro> incontroOptional = incontroDao.findById(id);
        if (incontroOptional.isPresent()) {
            Incontro incontro = incontroOptional.get(); // prendiamo l'incontro

            // rimozione incontro dagli aabobnamenti associati ad esso
            for (Abbonamento abbonamento : incontro.getAbbonamenti()) {
                abbonamento.getIncontri().remove(incontro);
                abbonamentoDao.save(abbonamento);  // salvare abbonamento aggiornato
            }

            // eliminare l'incontro solo dopo aver rimosso i riferimenti da tutti gli abbonamenti
            incontroDao.delete(incontro);
            System.out.println("Incontro eliminato con successo");
        } else {
            System.out.println("Incontro non trovato con l'ID: " + id);
        }
    }
}
