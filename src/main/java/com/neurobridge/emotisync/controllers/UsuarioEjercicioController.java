package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.EjercicioCompletadoDTO;
import com.neurobridge.emotisync.dtos.PacienteListDTO;
import com.neurobridge.emotisync.dtos.TotalPacienteDTO;
import com.neurobridge.emotisync.dtos.UsuarioEjercicioDTO;
import com.neurobridge.emotisync.entities.Ejercicio;
import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.entities.UsuarioEjercicio;
import com.neurobridge.emotisync.servicesinterfaces.IUsuarioEjercicioService;
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
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/usuarioejercicios")
public class UsuarioEjercicioController {
    @Autowired
    private IUsuarioEjercicioService service;

    @GetMapping
    public List<UsuarioEjercicioDTO> listar(){
        return service.getUsuarioEjercicios().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,UsuarioEjercicioDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody UsuarioEjercicioDTO u){
        ModelMapper m = new ModelMapper();
        UsuarioEjercicio usuarioEjercicio=m.map(u, UsuarioEjercicio.class);
        service.insert(usuarioEjercicio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        UsuarioEjercicio usuarioEjercicio = service.listId(id);
        if (usuarioEjercicio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody UsuarioEjercicioDTO dto) {
        ModelMapper m = new ModelMapper();
        UsuarioEjercicio s = m.map(dto, UsuarioEjercicio.class);
        UsuarioEjercicio existente = service.listId(s.getIdUsuarioEjercicio());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + s.getIdEjercicio());
        }
        service.update(s);
        return ResponseEntity.ok("Registro con ID " + s.getIdEjercicio() + " modificado correctamente.");
    }

    @GetMapping("/ejercicioscompletados")
    public ResponseEntity<?> ejerciciosRealizadosPorUsuario(){
        List<String[]> total = service.ejerciciosRealizadosPorUsuario();
        List<EjercicioCompletadoDTO> dtoList = new ArrayList<>();

        for (String[] columna : total) {
            EjercicioCompletadoDTO dto = new EjercicioCompletadoDTO();
            dto.setUsuarioId(Integer.parseInt(columna[0]));
            dto.setNombre(columna[1]);
            dto.setApellido(columna[2]);
            dto.setEjerciciosCompletados(Integer.parseInt(columna[3]));
            dtoList.add(dto);
        }
        return ResponseEntity.ok(dtoList);
    }

}
