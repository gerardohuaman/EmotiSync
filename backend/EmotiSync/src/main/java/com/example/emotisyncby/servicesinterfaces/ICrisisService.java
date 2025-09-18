package com.example.emotisyncby.servicesinterfaces;

import com.example.emotisyncby.entities.Crisis;

import java.util.List;

public interface ICrisisService {
    //CRUD
    public void insert(Crisis crisis);
    public List<Crisis> list();
    public void update(Crisis crisis);
    public void delete(int id);

    //extra
    public Crisis listId(int id);
}
