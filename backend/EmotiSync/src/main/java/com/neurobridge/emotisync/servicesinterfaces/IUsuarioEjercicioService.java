package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Ejercicio;
import com.neurobridge.emotisync.entities.UsuarioEjercicio;

import java.util.List;

public interface IUsuarioEjercicioService {
    public List<UsuarioEjercicio> getUsuarioEjercicios();
    public void insert(UsuarioEjercicio usuarioEjercicio);
    public UsuarioEjercicio listId(int id);
    public void update(UsuarioEjercicio usuarioEjercicio);
    public void delete(int id);
    public List<String[]> ejerciciosRealizadosPorUsuario();
}
