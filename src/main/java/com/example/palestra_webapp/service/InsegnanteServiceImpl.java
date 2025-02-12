package com.example.palestra_webapp.service;

import com.example.palestra_webapp.dao.InsegnanteDao;
import com.example.palestra_webapp.model.Insegnante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsegnanteServiceImpl implements InsegnanteService {

    @Autowired
    private InsegnanteDao insegnanteDao;

    @Override
    public List<Insegnante> elencoInsegnanti() {
        List<Insegnante> insegnanti = (List<Insegnante>) insegnanteDao.findAll();

        // Imposta manualmente le descrizioni e le foto
        for (Insegnante insegnante : insegnanti) {
            if (insegnante.getNome().equals("Ginevra") && insegnante.getCognome().equals("Ruggeri")) {
                insegnante.setDescrizione("6 anni di esperienza in Pilates. Specializzata in...");
                insegnante.setFotoUrl("/img/ins2Pilates.jpg");
                insegnante.setDisciplina("pilates");// Percorso relativo alla cartella static
            } else if (insegnante.getNome().equals("Marco") && insegnante.getCognome().equals("Verdi")) {
                insegnante.setDescrizione("12 anni di esperienza. Approccio innovativo...");
                insegnante.setFotoUrl("/img/instaichi.jpg");
                insegnante.setDisciplina("taichi");// Percorso relativo alla cartella static
            } else if (insegnante.getNome().equals("Elian") && insegnante.getCognome().equals("Varnesi")) {
                insegnante.setDescrizione("15 anni di esperienza in Tai Chi. Esperto in...");
                insegnante.setFotoUrl("/img/ins1Q.jpg");
                insegnante.setDisciplina("qigong");// Percorso relativo alla cartella static
            } else if (insegnante.getNome().equals("Anna") && insegnante.getCognome().equals("Rossi")) {
                insegnante.setDescrizione("Istruttrice di Yoga.");
                insegnante.setFotoUrl("/img/insYoga1.jpg");
                insegnante.setDisciplina("yoga");
            } else if (insegnante.getNome().equals("Luca") && insegnante.getCognome().equals("Bianchi")) {
                insegnante.setDescrizione("Personal Trainer");
                insegnante.setFotoUrl("/img/insPilates.jpg");
                insegnante.setDisciplina("pilates");
            } else {
                insegnante.setDescrizione("Descrizione predefinita per " + insegnante.getNome() + " " + insegnante.getCognome());
                insegnante.setFotoUrl("/img/default.jpg");
                insegnante.setDisciplina("default"); // Assicurati di avere un'immagine predefinita
            }
        }

        return insegnanti;
    }

    @Override
    public Insegnante datiInsegnante(int id) {
        Insegnante insegnante = insegnanteDao.findById(id).get();

        // Imposta manualmente la descrizione e la foto per il singolo insegnante
        if (insegnante.getNome().equals("Ginevra") && insegnante.getCognome().equals("Ruggeri")) {
            insegnante.setDescrizione("6 anni di esperienza in Pilates. Specializzata in...");
            insegnante.setFotoUrl("/img/ins2Pilates.jpg");
            insegnante.setDisciplina("pilates");// Percorso relativo alla cartella static
        }  else if (insegnante.getNome().equals("Marco") && insegnante.getCognome().equals("Verdi")) {
            insegnante.setDescrizione("12 anni di esperienza. Approccio innovativo...");
            insegnante.setFotoUrl("/img/instaichi.jpg");
            insegnante.setDisciplina("taichi");
        } else if (insegnante.getNome().equals("Elian") && insegnante.getCognome().equals("Varnesi")) {
            insegnante.setDescrizione("15 anni di esperienza in Tai Chi. Esperto in...");
            insegnante.setFotoUrl("/img/ins1Q.jpg");
            insegnante.setDisciplina("qigong");
        } else if (insegnante.getNome().equals("Anna") && insegnante.getCognome().equals("Rossi")) {
            insegnante.setDescrizione("Istruttrice di Yoga.");
            insegnante.setFotoUrl("/img/insYoga1.jpg");
            insegnante.setDisciplina("yoga");
        } else if (insegnante.getNome().equals("Luca") && insegnante.getCognome().equals("Bianchi")) {
            insegnante.setDescrizione("Personal Trainer");
            insegnante.setFotoUrl("/img/insPilates.jpg");
            insegnante.setDisciplina("pilates");
        } else {
            insegnante.setDescrizione("Descrizione predefinita per " + insegnante.getNome() + " " + insegnante.getCognome());
            insegnante.setFotoUrl("/img/default.jpg");
            insegnante.setDisciplina("default");// Assicurati di avere un'immagine predefinita
        }

        return insegnante;
    }
}