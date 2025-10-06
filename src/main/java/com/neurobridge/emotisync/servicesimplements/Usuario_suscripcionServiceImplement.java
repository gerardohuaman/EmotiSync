package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.entities.Ejercicio;
import com.neurobridge.emotisync.entities.Usuario_suscripcion;
import com.neurobridge.emotisync.repositories.IUsuario_suscripcionRepository;
import com.neurobridge.emotisync.servicesinterfaces.IUsuario_suscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Usuario_suscripcionServiceImplement implements IUsuario_suscripcionService {
    @Autowired
    private IUsuario_suscripcionRepository repository;

    @Override
    public List<Usuario_suscripcion> list() {
        return repository.findAll();
    }

    @Override
    public void insert(Usuario_suscripcion usuario_suscripcion) {
        repository.save(usuario_suscripcion);
    }

    @Override
    public void update(Usuario_suscripcion usuario_suscripcion) {
        repository.save(usuario_suscripcion);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Usuario_suscripcion listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<String[]> buscarActivos() {
        return repository.buscarActivos();
    }

    @Override
    public List<String[]> buscarPorEmail(int id_usuario) {
        return repository.buscarPorEmail(id_usuario);
    }

    @Override
    public List<String[]> buscarPorIdPlanesSuscripcion() {
        return repository.buscarPorIdPlanesSuscripcion();
    }

}
