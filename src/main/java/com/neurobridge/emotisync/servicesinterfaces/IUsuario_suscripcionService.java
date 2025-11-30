package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Ejercicio;
import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.entities.Usuario_suscripcion;

import java.util.List;

public interface IUsuario_suscripcionService {

    public List<Usuario_suscripcion> list();
    public void insert(Usuario_suscripcion usuario_suscripcion);
    public void update(Usuario_suscripcion usuario_suscripcion);
    public void delete(int id);
    public Usuario_suscripcion listId(int id);
    public List<String[]> buscarActivos();
    public List<String[]> buscarPorEmail(String email);
    public List<String[]> buscarPorIdPlanesSuscripcion();
    public List<String[]> buscarPlanesMenosSuscriptoresActivos();
    List<Usuario_suscripcion> listarPorUsuario(String username);
}
