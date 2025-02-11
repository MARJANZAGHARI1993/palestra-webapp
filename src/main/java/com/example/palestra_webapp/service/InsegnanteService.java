package com.example.palestra_webapp.service;
import com.example.palestra_webapp.model.Insegnante;
import java.util.List;

public interface InsegnanteService {

    List<Insegnante> elencoInsegnanti();
    Insegnante datiInsegnante(int id);
}
