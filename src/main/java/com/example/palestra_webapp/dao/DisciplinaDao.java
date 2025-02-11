package com.example.palestra_webapp.dao;

import com.example.palestra_webapp.model.Disciplina;
import org.springframework.data.repository.CrudRepository;

public interface DisciplinaDao extends CrudRepository<Disciplina,Integer> {
}
