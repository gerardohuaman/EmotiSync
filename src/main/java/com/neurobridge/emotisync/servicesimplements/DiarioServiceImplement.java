package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.entities.Diario;
import com.neurobridge.emotisync.repositories.IDiarioRepository;
import com.neurobridge.emotisync.servicesinterfaces.IDiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiarioServiceImplement implements IDiarioService {
    @Autowired
    private IDiarioRepository diarioRepository;

    @Override
    public List<Diario> list() {
        return diarioRepository.findAll();
    }

    @Override
    public void insert(Diario d) {
        diarioRepository.save(d);
    }

    @Override
    public Diario listId(int id) {
        return diarioRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        diarioRepository.deleteById(id);
    }

    @Override
    public void update(Diario diario) {
        diarioRepository.save(diario);
    }

    @Override
    public List<Diario> listarPorUsuario(String username) {
        return diarioRepository.findByUsuario_Username(username);
    }


}
