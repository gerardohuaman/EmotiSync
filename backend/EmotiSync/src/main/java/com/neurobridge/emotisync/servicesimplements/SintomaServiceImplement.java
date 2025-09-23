package com.neurobridge.emotisync.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neurobridge.emotisync.entities.Sintoma;
import com.neurobridge.emotisync.repositories.ISintomaRepository;
import com.neurobridge.emotisync.servicesinterfaces.ISintomaService;

import java.util.List;

@Service
public class SintomaServiceImplement implements ISintomaService {

    @Autowired
    private ISintomaRepository sRepo;

    @Override
    public List<Sintoma> list() {
        return sRepo.findAll();
    }

    @Override
    public void insert(Sintoma s) {
        sRepo.save(s);
    }

    @Override
    public Sintoma listId(int id) {
        return sRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        sRepo.deleteById(id);
    }

    @Override
    public void update(Sintoma s) {
        sRepo.save(s);
    }
}
