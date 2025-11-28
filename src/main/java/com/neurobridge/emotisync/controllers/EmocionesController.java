package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.AverageDTOEmocionesInt;
import com.neurobridge.emotisync.dtos.EmocionesDTOInsert;
import com.neurobridge.emotisync.dtos.EmocionesDTOList;
import com.neurobridge.emotisync.dtos.QuantityDTOCrisis;
import com.neurobridge.emotisync.entities.Emociones;
import com.neurobridge.emotisync.servicesinterfaces.IEmocionesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("isAuthenticated()")
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
    public ResponseEntity<?> insertar(@RequestBody EmocionesDTOInsert emocionesDTOInsert) {
        ModelMapper m = new ModelMapper();
        Emociones e = m.map(emocionesDTOInsert, Emociones.class);
        emocionesService.insert(e);
        return ResponseEntity.ok("Registro con ID: " + e.getIdEmociones() + " creado correctamente.");
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

    //queries
    @GetMapping("/busquedaemoint5")
    public ResponseEntity<?> buscaremoint5() {
        List<Emociones> emociones = emocionesService.buscarEmocionesIntensidad5();
        if (emociones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro de emociones con intensidad 5");
        }
        List<EmocionesDTOList> listaDTO = emociones.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, EmocionesDTOList.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/promemociointen")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> promemociointen() {
        //List<Emociones> emociones = emocionesService.buscarPromedioEmocionesIntensidad();
        List<AverageDTOEmocionesInt> listaDTO = new ArrayList<>();
        List<String[]> fila = emocionesService.buscarPromedioEmocionesIntensidad();
        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un promedio de intensidad de emociones mayor a 5");
        }
        for(String[] columna:fila){
            AverageDTOEmocionesInt dto=new AverageDTOEmocionesInt();
            dto.setIntensidad(Integer.parseInt(columna[0]));
            dto.setPromedio(Float.parseFloat(columna[1]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }
}

























