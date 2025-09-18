package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Emociones;

import java.util.List;

public interface IEmocionesService {
    //create
    public void insert(Emociones emociones);

    //read
    public List<Emociones> list();

    //update
    public void update(Emociones emociones);

    //delete
    public void delete(int id);
    public Emociones listId(int id);
}
