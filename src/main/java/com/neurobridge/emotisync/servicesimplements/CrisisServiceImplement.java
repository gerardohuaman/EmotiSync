package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.entities.Crisis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neurobridge.emotisync.repositories.ICrisisRepository;
import com.neurobridge.emotisync.servicesinterfaces.ICrisisService;

import java.util.List;

@Service
public class CrisisServiceImplement implements ICrisisService {
    @Autowired
    private ICrisisRepository crisisRepository;

    // sale cuando se implements:

    @Override
    public void insert(Crisis crisis) {
        crisisRepository.save(crisis);
    }

    @Override
    public List<Crisis> list() {
        return crisisRepository.findAll();
    }

    @Override
    public void update(Crisis crisis) {
        crisisRepository.save(crisis);
    }

    @Override
    public void delete(int id) {
        crisisRepository.deleteById(id);
    }

    @Override
    public Crisis listId(int id) {
        return crisisRepository.findById(id).orElse(null);
    }
}
