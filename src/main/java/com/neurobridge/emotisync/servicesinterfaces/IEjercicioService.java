package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Ejercicio;

import java.util.List;

public interface IEjercicioService {
    public List<Ejercicio> getEjercicios();
    public void insert(Ejercicio ejercicio);
    public Ejercicio listId(int id);
    public void update(Ejercicio ejercicio);
    public void delete(int id);
    public List<Ejercicio> buscarEjercicioPorNOmbre(String nombre);
}
