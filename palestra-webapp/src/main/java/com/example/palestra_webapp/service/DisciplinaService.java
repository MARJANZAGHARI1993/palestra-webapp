package com.example.palestra_webapp.service;

import com.example.palestra_webapp.model.Disciplina;

import java.util.List;
import java.util.Optional;

public interface DisciplinaService {
    List<Disciplina> elencoDiscipline();
    Disciplina datiDisciplina(int id);
    Optional<Disciplina> getDisciplinaById(int id);
}