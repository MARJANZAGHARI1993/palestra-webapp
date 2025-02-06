package com.example.palestra_webapp.service;
import com.example.palestra_webapp.dao.CalendarioDao;
import com.example.palestra_webapp.model.Calendario;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


public class CalendarioServiceImpl implements CaldendarioService{

    @Autowired
    private CalendarioDao calendarioDao;

    @Override
    public List<Calendario> elencoCalendario() {
        return (List<Calendario>) calendarioDao.findAll();
    }

    @Override
    public Calendario datiCalendario(int id) {
        return calendarioDao.findById(id).get();
    }
}
