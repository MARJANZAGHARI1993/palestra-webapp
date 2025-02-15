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
                insegnante.setDescrizione("6 anni di esperienza in Pilates. Specializzata nel miglioramento della postura e nel rafforzamento muscolare, Ginevra utilizza tecniche avanzate per adattare ogni lezione alle esigenze individuali degli studenti.");
                insegnante.setFotoUrl("/img/ginevra.jpg");
            } else if (insegnante.getNome().equals("Marco") && insegnante.getCognome().equals("Verdi")) {
                insegnante.setDescrizione("Marco Verdi, con 12 anni di esperienza nel Pilates, utilizza attrezzi come il reformer e il Cadillac per migliorare forza, flessibilità e postura con programmi personalizzati.");
                insegnante.setFotoUrl("/img/instaichi.jpg");
            } else if (insegnante.getNome().equals("Beatrice") && insegnante.getCognome().equals("Verdi")) {
                insegnante.setDescrizione("12 anni di esperienza. Approccio innovativo che combina tecniche tradizionali e moderne per migliorare forza, flessibilità e consapevolezza corporea. Marco personalizza ogni lezione per ottimizzare i risultati individuali.");
                insegnante.setFotoUrl("/img/beatrice.jpg");
            } else if (insegnante.getNome().equals("Sara") && insegnante.getCognome().equals("Gialli")) {
                insegnante.setDescrizione("10 anni di esperienza nell'insegnamento dello yoga. Approccio innovativo che integra tecniche di rilassamento e potenziamento per promuovere equilibrio, flessibilità e benessere mentale.");
                insegnante.setFotoUrl("/img/sara.png");
            } else if (insegnante.getNome().equals("Elian") && insegnante.getCognome().equals("Varnesi")) {
                insegnante.setDescrizione("15 anni di esperienza nel Tai Chi. Esperto nella combinazione di movimenti fluidi e respirazione per migliorare l'equilibrio, la concentrazione e il benessere fisico.");
                insegnante.setFotoUrl("/img/elian.jpg");
            } else if (insegnante.getNome().equals("Elisa") && insegnante.getCognome().equals("Caruso")) {
                insegnante.setDescrizione("Istruttrice di Tai Chi con un approccio olistico, mirato a migliorare la postura, la mobilità articolare e la calma interiore.");
                insegnante.setFotoUrl("/img/elisa.jpg");
                insegnante.setDisciplina("taichi");
            } else if (insegnante.getNome().equals("Taren") && insegnante.getCognome().equals("Dravik")) {
                insegnante.setDescrizione("Insegnante di Qigong, specializzato nella pratica dell'energia vitale per il miglioramento del benessere fisico e mentale.");
                insegnante.setFotoUrl("/img/taren1.png");
            } else {
                insegnante.setDescrizione("Descrizione predefinita per " + insegnante.getNome() + " " + insegnante.getCognome());
                insegnante.setFotoUrl("/img/default.jpg");}
        }


        return insegnanti;
    }

    @Override
    public Insegnante datiInsegnante(int id) {
        Insegnante insegnante = insegnanteDao.findById(id).get();

        // Imposta manualmente la descrizione e la foto per il singolo insegnante
        if (insegnante.getNome().equals("Ginevra") && insegnante.getCognome().equals("Ruggeri")) {
            insegnante.setDescrizione("6 anni di esperienza in Pilates. Specializzata nel miglioramento della postura e nel rafforzamento muscolare, Ginevra utilizza tecniche avanzate per adattare ogni lezione alle esigenze individuali degli studenti.");
            insegnante.setFotoUrl("/img/ginevra.jpg");
        } else if (insegnante.getNome().equals("Marco") && insegnante.getCognome().equals("Verdi")) {
            insegnante.setDescrizione("Marco Verdi, con 12 anni di esperienza nel Pilates, utilizza attrezzi come il reformer e il Cadillac per migliorare forza, flessibilità e postura con programmi personalizzati.");
            insegnante.setFotoUrl("/img/instaichi.jpg");
        } else if (insegnante.getNome().equals("Beatrice") && insegnante.getCognome().equals("Verdi")) {
            insegnante.setDescrizione("12 anni di esperienza. Approccio innovativo che combina tecniche tradizionali e moderne per migliorare forza, flessibilità e consapevolezza corporea. Marco personalizza ogni lezione per ottimizzare i risultati individuali.");
            insegnante.setFotoUrl("/img/beatrice.jpg");
        } else if (insegnante.getNome().equals("Sara") && insegnante.getCognome().equals("Gialli")) {
            insegnante.setDescrizione("10 anni di esperienza nell'insegnamento dello yoga. Approccio innovativo che integra tecniche di rilassamento e potenziamento per promuovere equilibrio, flessibilità e benessere mentale.");
            insegnante.setFotoUrl("/img/sara.png");
        } else if (insegnante.getNome().equals("Elian") && insegnante.getCognome().equals("Varnesi")) {
            insegnante.setDescrizione("15 anni di esperienza nel Tai Chi. Esperto nella combinazione di movimenti fluidi e respirazione per migliorare l'equilibrio, la concentrazione e il benessere fisico.");
            insegnante.setFotoUrl("/img/elian.jpg");
        } else if (insegnante.getNome().equals("Elisa") && insegnante.getCognome().equals("Caruso")) {
            insegnante.setDescrizione("Istruttrice di Tai Chi con un approccio olistico, mirato a migliorare la postura, la mobilità articolare e la calma interiore.");
            insegnante.setFotoUrl("/img/elisa.jpg");
        } else if (insegnante.getNome().equals("Taren") && insegnante.getCognome().equals("Dravik")) {
            insegnante.setDescrizione("Insegnante di Qigong, specializzato nella pratica dell'energia vitale per il miglioramento del benessere fisico e mentale.");
            insegnante.setFotoUrl("/img/taren1.png");
        } else {
            insegnante.setDescrizione("Descrizione predefinita per " + insegnante.getNome() + " " + insegnante.getCognome());
            insegnante.setFotoUrl("/img/default.jpg");
        }

        return insegnante;
    }
}

