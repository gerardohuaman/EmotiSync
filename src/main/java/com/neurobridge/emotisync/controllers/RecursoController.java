package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.RecursoPromedioDTO;
import com.neurobridge.emotisync.dtos.RecursoRelacionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.neurobridge.emotisync.dtos.RecursoDTO;
import com.neurobridge.emotisync.entities.Recurso;
import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.servicesinterfaces.IRecursoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recursos")
public class RecursoController {
    @Autowired
    private IRecursoService rService;

    @GetMapping
    public List<RecursoDTO> listar() {
        // --- CÓDIGO ARREGLADO ---
        // Deja que ModelMapper maneje el mapeo anidado
        return rService.list().stream().map(r -> {
            ModelMapper m = new ModelMapper();
            RecursoDTO dto = m.map(r, RecursoDTO.class);
            // Ya no necesitas las líneas setCreadorId()
            return dto;
        }).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Recurso r = rService.listId(id);
        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un recurso con el ID: " + id);
        }
        // --- CÓDIGO ARREGLADO ---
        ModelMapper m = new ModelMapper();
        RecursoDTO dto = m.map(r, RecursoDTO.class);
        // Ya no necesitas las líneas setCreadorId()
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<String> insertar(@RequestBody RecursoDTO dto) {

        ModelMapper m = new ModelMapper();
        Recurso r = m.map(dto, Recurso.class);
        rService.insert(r);
        return ResponseEntity.status(HttpStatus.CREATED).body("Recurso creado correctamente");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody RecursoDTO dto) {
        Recurso existente = rService.listId(dto.getId());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar: no existe el ID " + dto.getId());
        }

        ModelMapper m = new ModelMapper();
        Recurso r = m.map(dto, Recurso.class);

        rService.update(r);
        return ResponseEntity.ok("Recurso modificado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Recurso r = rService.listId(id);
        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró un recurso con el ID: " + id);
        }
        rService.delete(id);
        return ResponseEntity.ok("Recurso eliminado correctamente");
    }

    @GetMapping("/relacion")
    public ResponseEntity<RecursoRelacionDTO> verificarRelacion(
            @RequestParam int creadorId,
            @RequestParam int destinatarioId) {

        boolean existe = rService.existeRelacionEntreUsuarios(creadorId, destinatarioId);

        RecursoRelacionDTO dto = new RecursoRelacionDTO();
        dto.setCreadorId(creadorId);
        dto.setDestinatarioId(destinatarioId);
        dto.setExisteRelacion(existe);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/promedio")
    public ResponseEntity<List<RecursoPromedioDTO>> promedioRecursos() {
        List<RecursoPromedioDTO> lista = rService.promedioRecursosPorCreador();
        return ResponseEntity.ok(lista);
    }


}