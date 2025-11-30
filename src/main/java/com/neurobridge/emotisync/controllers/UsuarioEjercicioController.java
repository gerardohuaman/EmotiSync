package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.*;
import com.neurobridge.emotisync.entities.UsuarioEjercicio;
import com.neurobridge.emotisync.servicesinterfaces.IUsuarioEjercicioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarioejercicios")
@PreAuthorize("isAuthenticated()")
public class UsuarioEjercicioController {
    @Autowired
    private IUsuarioEjercicioService service;

    @GetMapping
    public List<UsuarioEjercicioDTO> listar(){
        // 1. Identificar quién pide los datos
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // 2. Verificar Rol
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
        boolean isEspecialista = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ESPECIALISTA"));

        List<UsuarioEjercicio> lista;

        // 3. Filtrar Lógica
        if (isAdmin || isEspecialista) {
            // TODO: Idealmente el especialista solo debería ver los de SUS pacientes,
            // pero por ahora dejamos que vea todos para simplificar la demo.
            lista = service.getUsuarioEjercicios();
        } else {
            // Es PACIENTE -> Solo ve lo suyo
            lista = service.listarPorUsuario(username);
        }
        return service.getUsuarioEjercicios().stream().map(x->{
            ModelMapper m = new ModelMapper();
            UsuarioEjercicioDTO dto = m.map(x, UsuarioEjercicioDTO.class);
            if(x.getUsuario() != null) dto.setUsuario(m.map(x.getUsuario(), UsuarioListDTO.class));
            if(x.getEjercicio() != null) dto.setEjercicio(m.map(x.getEjercicio(), EjercicioDTO.class));
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN', 'ESPECIALISTA')")
    public ResponseEntity<String> insertar(@RequestBody UsuarioEjercicioInsertDTO u){
        ModelMapper m = new ModelMapper();
        UsuarioEjercicio usuarioEjercicio=m.map(u, UsuarioEjercicio.class);
        service.insert(usuarioEjercicio);
        return ResponseEntity.ok("Ejercicio asignado correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        UsuarioEjercicio usuarioEjercicio = service.listId(id);
        if (usuarioEjercicio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        UsuarioEjercicioDTO dto = m.map(usuarioEjercicio, UsuarioEjercicioDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN', 'ESPECIALISTA')")
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
    public ResponseEntity<String> modificar(@RequestBody UsuarioEjercicioInsertDTO dto) {
        UsuarioEjercicio existente = service.listId(dto.getIdUsuarioEjercicio());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe el registro ID: " + dto.getIdUsuarioEjercicio());
        }

        // Aquí podrías agregar validación: "Si es paciente, solo puede modificar si el ejercicio es suyo"

        ModelMapper m = new ModelMapper();
        UsuarioEjercicio s = m.map(dto, UsuarioEjercicio.class);

        // Truco: Mantener el usuario y ejercicio original si el DTO viene vacío en esos campos
        if(s.getUsuario() == null) s.setUsuario(existente.getUsuario());
        if(s.getEjercicio() == null) s.setEjercicio(existente.getEjercicio());

        service.update(s);
        return ResponseEntity.ok("Ejercicio actualizado correctamente.");
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
