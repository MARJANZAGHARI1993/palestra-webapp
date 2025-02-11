package com.example.palestra_webapp.dao;

import com.example.palestra_webapp.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteDao extends CrudRepository<Utente, Integer> {
    // Modifica i metodi per usare 'username' e 'password' correttamente
    Utente findByUsernameAndPasswordUtente(String username, String password); // Cerca per username e password
    Utente findByUsername(String username); // Cerca per username
}
