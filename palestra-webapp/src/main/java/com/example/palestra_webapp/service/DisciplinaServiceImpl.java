package com.example.palestra_webapp.service;

import com.example.palestra_webapp.dao.DisciplinaDao;
import com.example.palestra_webapp.model.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    @Autowired
    private DisciplinaDao disciplinaDao;

    @Override
    public List<Disciplina> elencoDiscipline() {
        return (List<Disciplina>) disciplinaDao.findAll();
    }

    @Override
    public Disciplina datiDisciplina(int id) {
        return disciplinaDao.findById(id).get();
    }

    @Override
    public Optional<Disciplina> getDisciplinaById(int id) {
        try {
            Optional<Disciplina> disciplinaOptional = disciplinaDao.findById(id);
            if (disciplinaOptional.isPresent()) {
                return Optional.of(disciplinaOptional.get());
            } else {
                System.out.println("Nessuna disciplina trovata con l'ID: " + id);
                return Optional.empty();
            }
        } catch (Exception e) {
            System.out.println("Errore nella ricerca della disciplina: " + e.getMessage());
            return Optional.empty();
        }
    }
}
