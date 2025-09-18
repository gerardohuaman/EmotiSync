package com.example.emotisyncby.controllers;

import com.example.emotisyncby.dto.EmocionesDTOList;
import com.example.emotisyncby.entities.Emociones;
import com.example.emotisyncby.servicesinterfaces.IEmocionesService;
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

























