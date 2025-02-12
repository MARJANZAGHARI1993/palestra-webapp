package com.example.palestra_webapp.dao;

import com.example.palestra_webapp.model.Disciplina;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DisciplinaDao extends CrudRepository<Disciplina,Integer> {
    Optional<Disciplina> findByNome(String nome);
}
