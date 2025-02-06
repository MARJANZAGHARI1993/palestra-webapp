package com.example.palestra_webapp.service;
import com.example.palestra_webapp.dao.InsegnanteDao;
import com.example.palestra_webapp.model.Insegnante;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class InsegnanteServiceImpl implements InsegnanteService {

    @Autowired
    private InsegnanteDao insegnanteDao;

    @Override
    public List<Insegnante> elencoInsegnanti() {
        return (List<Insegnante>) insegnanteDao.findAll();
    }

    @Override
    public Insegnante datiInsegnante(int id) {
        return insegnanteDao.findById(id).get();
    }
}
