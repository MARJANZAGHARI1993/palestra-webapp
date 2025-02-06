package com.example.palestra_webapp.dao;

import com.example.palestra_webapp.model.Insegnante;
import org.springframework.data.repository.CrudRepository;

public interface InsegnanteDao extends CrudRepository<Insegnante,Integer> {
}
