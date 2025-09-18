package com.example.emotisyncby.servicesimplements;

import com.example.emotisyncby.entities.Crisis;
import com.example.emotisyncby.entities.Emociones;
import com.example.emotisyncby.repositories.IEmocionesRepository;
import com.example.emotisyncby.servicesinterfaces.ICrisisService;
import com.example.emotisyncby.servicesinterfaces.IEmocionesService;
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

    @Override
    public Emociones listId(int id) {
        return emocionesRepository.findById(id).orElse(null);
    }
}
