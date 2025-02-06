package com.example.palestra_webapp.service;

import com.example.palestra_webapp.model.Disciplina;
import java.util.List;

public interface DisciplinaService {
    List<Disciplina> elencoDiscipline();
    Disciplina datiDisciplina(int id);
}


