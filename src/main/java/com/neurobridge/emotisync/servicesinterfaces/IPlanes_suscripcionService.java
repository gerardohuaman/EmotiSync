package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Susbcription;

import java.util.List;

public interface IPlanes_suscripcionService {
    public List<Susbcription> list();
    public void insert(Susbcription suscripcion);

    public Susbcription listId(int id);
    public List<Susbcription> buscarPlanSuscripcion(String name);
}
