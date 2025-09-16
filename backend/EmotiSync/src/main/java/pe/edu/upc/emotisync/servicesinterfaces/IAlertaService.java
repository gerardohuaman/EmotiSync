package pe.edu.upc.emotisync.servicesinterfaces;

import pe.edu.upc.emotisync.entities.Alertas;

import java.util.List;

public interface IAlertaService {
    public List<Alertas> list();
    public void insert(Alertas alerta);
}
