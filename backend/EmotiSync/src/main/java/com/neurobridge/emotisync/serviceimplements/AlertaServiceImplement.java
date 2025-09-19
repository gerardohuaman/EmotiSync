package com.neurobridge.emotisync.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neurobridge.emotisync.entities.Alertas;
import com.neurobridge.emotisync.repositories.IAlertaRepository;
import com.neurobridge.emotisync.servicesinterfaces.IAlertaService;

import java.util.List;

@Service
public class AlertaServiceImplement implements IAlertaService {

    @Autowired
    private IAlertaRepository repository;

    @Override
    public List<Alertas> list() {
        return repository.findAll();
    }

    @Override
    public void insert(Alertas alerta) { repository.save(alerta);    }
}
