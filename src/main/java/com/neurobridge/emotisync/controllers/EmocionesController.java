package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.EmocionesDTOList;
import com.neurobridge.emotisync.entities.Emociones;
import com.neurobridge.emotisync.servicesinterfaces.IEmocionesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emociones")
public class EmocionesController {
    @Autowired
    private IEmocionesService emocionesService;

    //read
    @GetMapping
    public List<EmocionesDTOList> listar(){
    return emocionesService.list().stream().map(x->{
        ModelMapper m = new ModelMapper();
        return m.map(x, EmocionesDTOList.class);
    }).collect(Collectors.toList());
    }

    //create
    @PostMapping
    public void insertar(@RequestBody EmocionesDTOList emocionesDTOInsert){
        ModelMapper m = new ModelMapper();
        Emociones e = m.map(emocionesDTOInsert, Emociones.class);
        emocionesService.insert(e);
    }

}

























