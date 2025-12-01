package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.RolInsertDTO;
import com.neurobridge.emotisync.dtos.RolListDTO;
import com.neurobridge.emotisync.entities.Rol;
import com.neurobridge.emotisync.servicesinterfaces.IRolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/roles")
public class RolController {
    @Autowired
    private IRolService rolService;
    
    @GetMapping
    public List<RolListDTO> findAll() {
        return rolService.findAll().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, RolListDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody RolInsertDTO u){
        ModelMapper m = new ModelMapper();
        Rol rol=m.map(u, Rol.class);
        rolService.insert(rol);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Rol r = rolService.findById(id);
        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        RolListDTO dto = m.map(r, RolListDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Rol s = rolService.findById(id);
        if (s == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        rolService.deleteById(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody RolInsertDTO dto) {
        ModelMapper m = new ModelMapper();
        Rol r = m.map(dto, Rol.class);
        Rol existente = rolService.findById(r.getIdRol());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + r.getRol());
        }
        rolService.update(r);
        return ResponseEntity.ok("Registro con ID " + r.getIdRol() + " modificado correctamente.");
    }

}
