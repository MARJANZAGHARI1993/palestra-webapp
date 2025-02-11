package com.example.palestra_webapp.dao;

import com.example.palestra_webapp.model.Calendario;
import org.springframework.data.repository.CrudRepository;

public interface CalendarioDao extends CrudRepository<Calendario,Integer> {
}
