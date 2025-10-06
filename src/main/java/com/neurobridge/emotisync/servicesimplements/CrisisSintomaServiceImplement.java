package com.neurobridge.emotisync.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neurobridge.emotisync.entities.CrisisSintoma;
import com.neurobridge.emotisync.repositories.ICrisisSintomaRepository;
import com.neurobridge.emotisync.servicesinterfaces.ICrisisSintomaService;

import java.util.List;

@Service
public class CrisisSintomaServiceImplement implements ICrisisSintomaService {

    @Autowired
    private ICrisisSintomaRepository csRepo;

    @Override
    public List<CrisisSintoma> list() {
        return csRepo.findAll();
    }

    @Override
    public void insert(CrisisSintoma cs) {
        csRepo.save(cs);
    }

    @Override
    public CrisisSintoma listId(int id) {
        return csRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        csRepo.deleteById(id);
    }

    @Override
    public void update(CrisisSintoma cs) {
        csRepo.save(cs);
    }
}
