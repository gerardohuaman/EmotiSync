package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.Planes_suscripcionDTO;
import com.neurobridge.emotisync.entities.Planes_suscripcion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.neurobridge.emotisync.dtos.SintomaDTO;
import com.neurobridge.emotisync.entities.Sintoma;
import com.neurobridge.emotisync.servicesinterfaces.ISintomaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/sintomas")
public class SintomaController {
    @Autowired
    private ISintomaService sService;

    @GetMapping
    public List<SintomaDTO> listar() {
        return sService.list().stream().map(s -> {
            ModelMapper m = new ModelMapper();
            return m.map(s, SintomaDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<String> insertar(@RequestBody SintomaDTO dto) {
        ModelMapper m = new ModelMapper();
        Sintoma s = m.map(dto, Sintoma.class);
        sService.insert(s);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Síntoma creado correctamente");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody SintomaDTO dto) {
        Sintoma existente = sService.listId(dto.getId());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar: no existe el ID " + dto.getId());
        }
        ModelMapper m = new ModelMapper();
        Sintoma s = m.map(dto, Sintoma.class);
        sService.update(s);
        return ResponseEntity.ok("Síntoma modificado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Sintoma s = sService.listId(id);
        if (s == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró un síntoma con el ID: " + id);
        }
        sService.delete(id);
        return ResponseEntity.ok("Síntoma eliminado correctamente");
    }

    // ---- Búsqueda ----
    @GetMapping("/buscar-nombre/{nombre}")
    public List<SintomaDTO> buscarPorNombre(@PathVariable String nombre) {
        return sService.buscarPorNombre(nombre).stream()
                .map(s -> new ModelMapper().map(s, SintomaDTO.class))
                .toList();
    }

    @GetMapping("/buscar-descripcion/{desc}")
    public List<SintomaDTO> buscarPorDescripcion(@PathVariable String desc) {
        return sService.buscarPorDescripcion(desc).stream()
                .map(s -> new ModelMapper().map(s, SintomaDTO.class))
                .toList();
    }

    // ---- Decisión ----
    @GetMapping("/count")
    public long contarTotal() {
        return sService.contarTotal();
    }

    @GetMapping("/existe/{nombre}")
    public boolean existePorNombre(@PathVariable String nombre) {
        return sService.existePorNombre(nombre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Sintoma s = sService.listId(id);
        if (s == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        SintomaDTO dto = m.map(s, SintomaDTO.class);
        return ResponseEntity.ok(dto);
    }
}
