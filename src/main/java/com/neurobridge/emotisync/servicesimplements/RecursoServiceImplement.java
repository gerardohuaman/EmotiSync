package com.neurobridge.emotisync.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neurobridge.emotisync.entities.Recurso;
import com.neurobridge.emotisync.repositories.IRecursoRepository;
import com.neurobridge.emotisync.servicesinterfaces.IRecursoService;

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
}
