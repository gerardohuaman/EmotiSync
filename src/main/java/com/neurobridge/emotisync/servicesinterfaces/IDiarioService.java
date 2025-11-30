package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Diario;

import java.util.List;

public interface IDiarioService {
    List<Diario> list();
    void insert(Diario diario);
    Diario listId(int id);
    void delete(int id);
    void update(Diario diario);
    List<Diario> listarPorUsuario(String username);
}
