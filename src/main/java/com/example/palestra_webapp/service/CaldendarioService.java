package com.example.palestra_webapp.service;
import com.example.palestra_webapp.model.Calendario;
import java.util.List;

public interface CaldendarioService {
    List<Calendario> elencoCalendario();
    Calendario datiCalendario(int id);

}
