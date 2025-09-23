package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.EmocionesDTOInsert;
import com.neurobridge.emotisync.dtos.EmocionesDTOList;
import com.neurobridge.emotisync.entities.Emociones;
import com.neurobridge.emotisync.servicesinterfaces.IEmocionesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<EmocionesDTOList> listar() {
        return emocionesService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, EmocionesDTOList.class);
        }).collect(Collectors.toList());
    }

    //create
    @PostMapping
    public void insertar(@RequestBody EmocionesDTOInsert emocionesDTOInsert) {
        ModelMapper m = new ModelMapper();
        Emociones e = m.map(emocionesDTOInsert, Emociones.class);
        emocionesService.insert(e);
    }

    //update
    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody EmocionesDTOInsert emocionesDTOInsert) {
        ModelMapper m = new ModelMapper();
        Emociones e = m.map(emocionesDTOInsert, Emociones.class);
        Emociones existente = emocionesService.listId(e.getIdEmociones());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("No se puede modificar. No existe un regustro con ID: " + e.getIdEmociones());
        }
        emocionesService.update(e);
        return ResponseEntity.ok("Registro con ID: " + e.getIdEmociones() + "modificado correctamente.");
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Emociones e = emocionesService.listId(id);
        if (e == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con ID: " + id);
        }
        emocionesService.delete(id);
        return ResponseEntity.ok("Registro con ID: " + id + "eliminado correctamente.");
    }

    //extra pal delete
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Emociones e = emocionesService.listId(id);
        if (e == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        EmocionesDTOList dto = m.map(e, EmocionesDTOList.class);
        return ResponseEntity.ok(dto);
    }

    //queries
    @GetMapping("/busquedaemoint5")
    public ResponseEntity<?> buscaremoint5() {
        List<Emociones> emociones = emocionesService.buscarEmocionesIntensidad5();
        if (emociones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro de emocione con intensidad 5");
        }
        List<EmocionesDTOList> listaDTO = emociones.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, EmocionesDTOList.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);

    }
}

























