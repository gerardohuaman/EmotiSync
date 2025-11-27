package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.Usuario_suscripcionDTO;
import com.neurobridge.emotisync.entities.Planes_suscripcion;
import com.neurobridge.emotisync.entities.Usuario_suscripcion;
import org.modelmapper.ModelMapper;
import com.neurobridge.emotisync.dtos.Planes_suscripcionDTO;
import com.neurobridge.emotisync.servicesinterfaces.IPlanes_suscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/planesSuscripcion")
public class Planes_suscripcionController {
    @Autowired
    private IPlanes_suscripcionService pS;

    @GetMapping
    public List<Planes_suscripcionDTO> listar(){
        return pS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,Planes_suscripcionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody Planes_suscripcion planesDTO) {
        ModelMapper m = new ModelMapper();
        Planes_suscripcionDTO dto = m.map(planesDTO,Planes_suscripcionDTO.class);
        pS.insert(planesDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        Planes_suscripcion s = pS.listId(id);
        if(s == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el plan de suscripcion con el ID: " + id);
        }
        pS.delete(id);
        return ResponseEntity.ok().body("Plan de suscripcion con ID: " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody Planes_suscripcionDTO dto){
        ModelMapper m = new ModelMapper();
        Planes_suscripcion s = m.map(dto, Planes_suscripcion.class);
        Planes_suscripcion exists = pS.listId(s.getIdPlanesSuscripcion());
        if(exists == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un plan de suscripcion con el ID: " + s.getIdPlanesSuscripcion());
        }
        pS.update(s);
        return ResponseEntity.ok("Plan de suscripcion con ID: " + s.getIdPlanesSuscripcion() + " actualizado correctamente.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Planes_suscripcion s = pS.listId(id);
        if (s == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        Planes_suscripcionDTO dto = m.map(s, Planes_suscripcionDTO.class);
        return ResponseEntity.ok(dto);
    }
}
