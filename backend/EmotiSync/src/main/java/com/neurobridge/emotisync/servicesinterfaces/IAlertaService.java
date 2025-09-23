package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Alertas;
import com.neurobridge.emotisync.entities.Ejercicio;

import java.util.List;

public interface IAlertaService {
    public List<Alertas> list();
    public void insert(Alertas alerta);
    public Alertas listId(int id);
    public void update(Alertas alerta);
    public void delete(int id);
    public List<Alertas> searchAlertasUser(int  idUsuario);
    public List<Integer> searchUserCrisisFrecuentes(int idUsuario);
}
