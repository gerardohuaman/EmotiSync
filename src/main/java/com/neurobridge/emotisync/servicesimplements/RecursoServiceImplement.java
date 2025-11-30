package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.dtos.RecursoPromedioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neurobridge.emotisync.entities.Recurso;
import com.neurobridge.emotisync.repositories.IRecursoRepository;
import com.neurobridge.emotisync.servicesinterfaces.IRecursoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecursoServiceImplement implements IRecursoService {

    @Autowired
    private IRecursoRepository rRepo;

    @Override
    public List<Recurso> list() {
        return rRepo.findAll();
    }

    @Override
    public void insert(Recurso r) {
        rRepo.save(r);
    }

    @Override
    public Recurso listId(int id) {
        return rRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        rRepo.deleteById(id);
    }

    @Override
    public void update(Recurso r) {
        rRepo.save(r);
    }

    @Override
    public boolean existeRelacionEntreUsuarios(int creadorId, int destinatarioId) {
        return rRepo.existeRelacionEntreUsuarios(creadorId, destinatarioId);
    }

    @Override
    public List<RecursoPromedioDTO> promedioRecursosPorCreador() {
        List<Object[]> data = rRepo.promedioRecursosPorCreador();
        List<RecursoPromedioDTO> lista = new ArrayList<>();

        for (Object[] fila : data) {
            RecursoPromedioDTO dto = new RecursoPromedioDTO();
            dto.setNombreCreador((String) fila[0]);
            dto.setPromedioRecursos((Double) fila[1]);
            lista.add(dto);
        }
        return lista;
    }

    @Override
    public List<Recurso> listarPorCreador(String username) {
        return rRepo.findByCreador_Username(username);
    }


}
