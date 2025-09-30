package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.entities.Alertas;
import com.neurobridge.emotisync.servicesinterfaces.IAlertaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.neurobridge.emotisync.dtos.AlertasDTOInsert;
import com.neurobridge.emotisync.dtos.AlertasDTOList;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alertas")
public class AlertasController {

    @Autowired
    private IAlertaService service;

    @GetMapping
    public List<AlertasDTOList> listar(){
        return service.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,AlertasDTOList.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody AlertasDTOInsert lic){
        ModelMapper m = new ModelMapper();
        Alertas l=m.map(lic, Alertas.class);
        service.insert(l);
    }
}
