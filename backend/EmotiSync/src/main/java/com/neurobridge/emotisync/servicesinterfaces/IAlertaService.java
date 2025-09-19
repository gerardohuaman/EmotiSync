package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Alertas;

import java.util.List;

public interface IAlertaService {
    public List<Alertas> list();
    public void insert(Alertas alerta);
}
