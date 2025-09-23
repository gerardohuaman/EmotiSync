package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.entities.Emociones;
import com.neurobridge.emotisync.repositories.IEmocionesRepository;
import com.neurobridge.emotisync.servicesinterfaces.IEmocionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmocionesServiceImplement implements IEmocionesService {
    @Autowired
    private IEmocionesRepository emocionesRepository;


    @Override
    public void insert(Emociones emociones) {
        emocionesRepository.save(emociones);
    }

    @Override
    public List<Emociones> list() {
        return emocionesRepository.findAll();
    }

    @Override
    public void update(Emociones emociones) {
        emocionesRepository.save(emociones);
    }

    @Override
    public void delete(int id) {
        emocionesRepository.deleteById(id);
    }

    //extra pal delete

    @Override
    public Emociones listId(int id) {
        return emocionesRepository.findById(id).orElse(null);
    }

    //queries

    @Override
    public List<Emociones> buscarEmocionesIntensidad5() {
        return emocionesRepository.buscarEmocionesIntensidad5();
    }

    @Override
    public List<Emociones> buscarEmocionesIntensidad(String nombre, float numero) {
        return emocionesRepository.buscarEmocionesIntensidad(nombre, numero);
    }
}
