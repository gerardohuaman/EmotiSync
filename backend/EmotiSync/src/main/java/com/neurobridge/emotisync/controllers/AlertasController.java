package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.EjercicioDTO;
import com.neurobridge.emotisync.entities.Ejercicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.neurobridge.emotisync.dtos.AlertasDTOInsert;
import com.neurobridge.emotisync.dtos.AlertasDTOList;
import com.neurobridge.emotisync.entities.Alertas;
import com.neurobridge.emotisync.servicesinterfaces.IAlertaService;

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
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Alertas alerta = service.listId(id);
        if (alerta == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        AlertasDTOList dto = m.map(alerta, AlertasDTOList.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Alertas alerta = service.listId(id);
        if (alerta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody AlertasDTOList dto) {
        ModelMapper m = new ModelMapper();
        Alertas s = m.map(dto, Alertas.class);
        Alertas existente = service.listId(s.getIdAlerta());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + s.getIdAlerta());
        }
        service.update(s);
        return ResponseEntity.ok("Registro con ID " + s.getIdAlerta() + " modificado correctamente.");
    }

}
