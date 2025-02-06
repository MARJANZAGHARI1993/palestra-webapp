package com.example.palestra_webapp.service;

import com.example.palestra_webapp.dao.DisciplinaDao;  // Importa il DAO (o Repository)
import com.example.palestra_webapp.model.Disciplina;
import com.example.palestra_webapp.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    @Autowired
    private DisciplinaDao disciplinaDao;

    // Implementazione del metodo per recuperare tutte le discipline
    @Override
    public List<Disciplina> elencoDiscipline() {
        return (List<Disciplina>) disciplinaDao.findAll();
    }

    // Implementazione del metodo per recuperare i dettagli di una disciplina
    @Override
    public Disciplina datiDisciplina(int id) {
        // Trova la disciplina per ID e restituisce un oggetto Optional
        Optional<Disciplina> disciplinaOptional = disciplinaDao.findById(id);
        // Restituisce la disciplina se trovata, altrimenti restituisce null
        return disciplinaOptional.orElse(null);
    }

}

