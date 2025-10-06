package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.entities.UsuarioEjercicio;
import com.neurobridge.emotisync.repositories.IUsuarioEjercicioRepository;
import com.neurobridge.emotisync.servicesinterfaces.IUsuarioEjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioEjercicioServiceImplement implements IUsuarioEjercicioService {
    @Autowired
    IUsuarioEjercicioRepository repository;

    @Override
    public List<UsuarioEjercicio> getUsuarioEjercicios() {
        return repository.findAll();
    }

    @Override
    public void insert(UsuarioEjercicio usuarioEjercicio) {
        repository.save(usuarioEjercicio);
    }

    @Override
    public UsuarioEjercicio listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(UsuarioEjercicio usuarioEjercicio) {
        repository.save(usuarioEjercicio);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<String[]> ejerciciosRealizadosPorUsuario() {
        return repository.ejerciciosPorUsuario();
    }
}
