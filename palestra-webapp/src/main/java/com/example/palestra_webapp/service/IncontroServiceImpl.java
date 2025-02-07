package com.example.palestra_webapp.service;

import com.example.palestra_webapp.dao.IncontroDao;
import com.example.palestra_webapp.model.Incontro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncontroServiceImpl implements IncontroService {

    @Autowired
    private IncontroDao incontroDao;


    @Override
    public Incontro aggiungiIncontro(Incontro incontro) {
        try {
            return incontroDao.save(incontro);
        } catch(Exception e) {
            System.out.println("C'è stato un errore" + e.getMessage());
        } return null;
    }

    @Override
    public Optional<Incontro> getIncontroById(int id) {
        try {
            Optional<Incontro> incontroOptional = incontroDao.findById((id));
            if (incontroOptional.isPresent()) {
                return Optional.of(incontroOptional.get());  // restituisce l'incontro che ha trovato (optional.of lo ha aggiunto intellij)
            } else {
                System.out.println("Nessun abbiamo trovato nessun incontro con l'ID: " + id);
                return null;
            }
        } catch (Exception e) {
            System.out.println("C'è stato un errore " + e.getMessage());
            return null;
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
            if (incontroTrovato != null) {
                incontro.setId(id);
                return incontroDao.save(incontro);
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
        if (incontroOptional.isPresent()) {  // vedere se è presente l'incontro
            Incontro incontro = incontroOptional.get(); // prendo l'incontro
            if (incontro.getAbbonamenti().isEmpty()) {  // vedere se ci sono abbonamenti
                incontroDao.delete(incontro);  //eliminare l'incontro se non ci sono abbonamenti
            }
        }
    }
}