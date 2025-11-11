package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.entities.Rol;
import com.neurobridge.emotisync.repositories.IRolRepository;
import com.neurobridge.emotisync.servicesinterfaces.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImplement implements IRolService {

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Rol findById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    public void insert(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public void deleteById(Integer id) {
        rolRepository.deleteById(id);
    }

    @Override
    public void update(Rol rol) {
        rolRepository.save(rol);
    }
}
