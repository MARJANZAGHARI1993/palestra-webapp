package com.example.palestra_webapp.dao;

import com.example.palestra_webapp.model.Abbonamento;
import org.springframework.data.repository.CrudRepository;

public interface AbbonamentoDao extends CrudRepository<Abbonamento,Integer> {
}
