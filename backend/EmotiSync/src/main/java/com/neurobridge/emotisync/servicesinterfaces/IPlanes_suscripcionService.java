package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Planes_suscripcion;

import java.util.List;

public interface IPlanes_suscripcionService {
    public List<Planes_suscripcion> list();
    public void insert(Planes_suscripcion suscripcion);
    public void update(Planes_suscripcion usuario_suscripcion);
    public void delete(int id);
    public Planes_suscripcion listId(int id);
    public List<Planes_suscripcion> buscarPlanSuscripcion(String name);
}
