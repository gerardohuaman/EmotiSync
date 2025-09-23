package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.entities.Susbcription;
import com.neurobridge.emotisync.repositories.ISuscriptionRepository;
import com.neurobridge.emotisync.servicesinterfaces.IPlanes_suscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Planes_suscripcionServiceImplement implements IPlanes_suscripcionService {
    @Autowired
    private ISuscriptionRepository sR;

    @Override
    public List<Susbcription> list() {
        return sR.findAll();
    }

    @Override
    public void insert(Susbcription suscripcion) {
        sR.save(suscripcion);
    }

    @Override
    public Susbcription listId(int id) {
        return sR.findById(id).orElse(null);
    }

    @Override
    public List<Susbcription> buscarPlanSuscripcion(String nSuscripcion) {
        return sR.buscar(nSuscripcion);
    }

}
