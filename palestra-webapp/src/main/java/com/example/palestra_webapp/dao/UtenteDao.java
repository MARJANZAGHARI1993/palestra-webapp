package com.example.palestra_webapp.dao;

import com.example.palestra_webapp.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteDao extends CrudRepository<Utente,Integer> {
}
