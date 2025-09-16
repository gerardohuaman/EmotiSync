package pe.edu.upc.emotisync.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.emotisync.dtos.AlertasDTOInsert;
import pe.edu.upc.emotisync.dtos.AlertasDTOList;
import pe.edu.upc.emotisync.entities.Alertas;
import pe.edu.upc.emotisync.servicesinterfaces.IAlertaService;

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
