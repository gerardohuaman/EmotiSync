package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.dtos.RecursoPromedioDTO;
import com.neurobridge.emotisync.entities.Recurso;
import java.util.List;

public interface IRecursoService {
    List<Recurso> list();
    void insert(Recurso r);
    Recurso listId(int id);
    void delete(int id);
    void update(Recurso r);
    boolean existeRelacionEntreUsuarios(int creadorId, int destinatarioId);
    List<RecursoPromedioDTO> promedioRecursosPorCreador();
    List<Recurso> listarPorCreador(String username);

}
