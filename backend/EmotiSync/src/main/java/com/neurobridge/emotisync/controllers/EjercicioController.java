package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.EjercicioDTO;
import com.neurobridge.emotisync.entities.Ejercicio;
import com.neurobridge.emotisync.servicesinterfaces.IEjercicioService;
import com.neurobridge.emotisync.servicesinterfaces.IEjercicioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ejercicios")
public class EjercicioController {
    @Autowired
    private IEjercicioService eS;

    @GetMapping
    public List<EjercicioDTO>listar(){
        return eS.getEjercicios().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,EjercicioDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody EjercicioDTO u){
        ModelMapper m = new ModelMapper();
        Ejercicio ejercicio=m.map(u, Ejercicio.class);
        eS.insert(ejercicio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Ejercicio ejercicio = eS.listId(id);
        if (ejercicio == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        EjercicioDTO dto = m.map(ejercicio, EjercicioDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Ejercicio ejercicio = eS.listId(id);
        if (ejercicio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        eS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody EjercicioDTO dto) {
        ModelMapper m = new ModelMapper();
        Ejercicio s = m.map(dto, Ejercicio.class);
        Ejercicio existente = eS.listId(s.getIdEjercicio());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + s.getIdEjercicio());
        }
        eS.update(s);
        return ResponseEntity.ok("Registro con ID " + s.getIdEjercicio() + " modificado correctamente.");
    }

}

