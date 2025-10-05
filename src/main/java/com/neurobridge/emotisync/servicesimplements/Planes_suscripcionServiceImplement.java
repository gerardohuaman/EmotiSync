package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.entities.Planes_suscripcion;
import com.neurobridge.emotisync.repositories.IPlanes_suscripcionRepository;
import com.neurobridge.emotisync.servicesinterfaces.IPlanes_suscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Planes_suscripcionServiceImplement implements IPlanes_suscripcionService {
    @Autowired
    private IPlanes_suscripcionRepository sR;

    @Override
    public List<Planes_suscripcion> list() {
        return sR.findAll();
    }

    @Override
    public void insert(Planes_suscripcion suscripcion) {
        sR.save(suscripcion);
    }

    @Override
    public void update(Planes_suscripcion usuario_suscripcion) {
        sR.save(usuario_suscripcion);
    }

    @Override
    public void delete(int id) {
        sR.deleteById(id);
    }

    @Override
    public Planes_suscripcion listId(int id) {
        return sR.findById(id).orElse(null);
    }

    @Override
    public List<Planes_suscripcion> buscarPlanSuscripcion(String nSuscripcion) {
        return sR.buscar(nSuscripcion);
    }

}
