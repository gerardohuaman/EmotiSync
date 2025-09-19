package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Usuario_suscripcion;

import java.util.List;

public interface IUsuario_suscripcionService {

    public List<Usuario_suscripcion> list();
    public void insert(Usuario_suscripcion usuario_suscripcion);
}
