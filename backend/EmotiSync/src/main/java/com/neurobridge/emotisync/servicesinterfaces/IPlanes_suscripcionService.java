package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Susbcription;
import com.neurobridge.emotisync.entities.Usuario_suscripcion;

import java.util.List;

public interface IPlanes_suscripcionService {
    public List<Susbcription> list();
    public void insert(Susbcription suscripcion);
    public void update(Susbcription usuario_suscripcion);
    public void delete(int id);
    public Susbcription listId(int id);
    public List<Susbcription> buscarPlanSuscripcion(String name);
}
