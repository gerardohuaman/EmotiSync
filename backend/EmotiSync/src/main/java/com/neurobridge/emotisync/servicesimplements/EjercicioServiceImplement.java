package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.entities.Alertas;
import com.neurobridge.emotisync.entities.Ejercicio;
import com.neurobridge.emotisync.repositories.IAlertaRepository;
import com.neurobridge.emotisync.repositories.IEjercicioRepository;
import com.neurobridge.emotisync.servicesinterfaces.IAlertaService;
import com.neurobridge.emotisync.servicesinterfaces.IEjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjercicioServiceImplement implements IEjercicioService {
    @Autowired
    private IEjercicioRepository ejercicioRepository;

    @Override
    public List<Ejercicio> getEjercicios() {
        return ejercicioRepository.findAll();
    }

    @Override
    public void insert(Ejercicio ejercicio) {
        ejercicioRepository.save(ejercicio);
    }

    @Override
    public Ejercicio listId(int id) {
        return ejercicioRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Ejercicio ejercicio) {
        ejercicioRepository.save(ejercicio);
    }

    @Override
    public void delete(int id) {
        ejercicioRepository.deleteById(id);
    }

}
