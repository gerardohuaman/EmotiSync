package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.CrisisSintomaDTO;
import com.neurobridge.emotisync.entities.Crisis;
import com.neurobridge.emotisync.entities.CrisisSintoma;
import com.neurobridge.emotisync.entities.Sintoma;
import com.neurobridge.emotisync.servicesinterfaces.ICrisisSintomaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/crisis-sintomas")
@PreAuthorize("hasAuthority('ADMIN')")
public class CrisisSintomaController {

    @Autowired
    private ICrisisSintomaService csService;

    @GetMapping
    public List<CrisisSintomaDTO> listar() {
        return csService.list().stream().map(cs -> {
            CrisisSintomaDTO dto = new CrisisSintomaDTO();
            dto.setId(cs.getId());
            dto.setCrisisId(cs.getCrisis().getIdCrisis());   // usar getIdCrisis()
            dto.setSintomaId(cs.getSintoma().getId());       // usar getId()
            dto.setSever(cs.getSever());
            dto.setObservacion(cs.getObservacion());
            dto.setNotas(cs.getNotas());
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<String> insertar(@RequestBody CrisisSintomaDTO dto) {
        Crisis crisis = new Crisis();
        crisis.setIdCrisis(dto.getCrisisId());   // aquí corregido

        Sintoma sintoma = new Sintoma();
        sintoma.setId(dto.getSintomaId());       // aquí está bien

        CrisisSintoma cs = new CrisisSintoma();
        cs.setCrisis(crisis);
        cs.setSintoma(sintoma);
        cs.setSever(dto.getSever());
        cs.setObservacion(dto.getObservacion());
        cs.setNotas(dto.getNotas());

        csService.insert(cs);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Relación Crisis-Síntoma creada correctamente");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody CrisisSintomaDTO dto) {
        CrisisSintoma existente = csService.listId(dto.getId());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar: no existe el ID " + dto.getId());
        }

        Crisis crisis = new Crisis();
        crisis.setIdCrisis(dto.getCrisisId());

        Sintoma sintoma = new Sintoma();
        sintoma.setId(dto.getSintomaId());

        existente.setCrisis(crisis);
        existente.setSintoma(sintoma);
        existente.setSever(dto.getSever());
        existente.setObservacion(dto.getObservacion());
        existente.setNotas(dto.getNotas());

        csService.update(existente);
        return ResponseEntity.ok("Relación Crisis-Síntoma modificada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        CrisisSintoma cs = csService.listId(id);
        if (cs == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una relación crisis-síntoma con ID: " + id);
        }
        csService.delete(id);
        return ResponseEntity.ok("Relación Crisis-Síntoma eliminada correctamente");
    }
}
