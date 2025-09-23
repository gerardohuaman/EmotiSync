package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Emociones;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmocionesService {
    //CRUD
    public void insert(Emociones emociones);
    public List<Emociones> list();
    public void update(Emociones emociones);
    public void delete(int id);

    //extra pal delete
    public Emociones listId(int id);

    //queries
    public List<Emociones> buscarEmocionesIntensidad5();
    public List<Emociones> buscarEmocionesIntensidad(String nombre, float numero);

}
