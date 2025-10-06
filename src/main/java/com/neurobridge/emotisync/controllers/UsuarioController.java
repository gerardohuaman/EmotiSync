package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.*;
import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.servicesinterfaces.IUsuarioService;
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
@RequestMapping("/usuarios")
@PreAuthorize("hasAuthority('ADMIN')")
public class UsuarioController {
    @Autowired
    private IUsuarioService uS;

    @GetMapping
    public List<UsuarioListDTO>listarUsuarios(){
        return uS.getUsuarios().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioListDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/pacientes")
    public List<PacienteListDTO>listarPaciente(){
        return uS.buscarPacientesService().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, PacienteListDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/especialistas")
    public List<EspecialistaDTO>listarEspecialista(){
        return uS.buscarEspecialista().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, EspecialistaDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/familiares")
    public List<FamiliarDTO>listarFamiliares(){
        return uS.buscarFamiliares().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, FamiliarDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody UsuarioInsertDTO u){
        ModelMapper m = new ModelMapper();
        Usuario usuario=m.map(u, Usuario.class);
        uS.insert(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Usuario s = uS.listId(id);
        if (s == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        uS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody UsuarioInsertDTO dto) {
        ModelMapper m = new ModelMapper();
        Usuario s = m.map(dto, Usuario.class);
        Usuario existente = uS.listId(s.getIdUsuario());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + s.getIdUsuario());
        }
        uS.update(s);
        return ResponseEntity.ok("Registro con ID " + s.getIdUsuario() + " modificado correctamente.");
    }

    @GetMapping("/pacientesPorMedico")
    public ResponseEntity<?> buscarPacientePorMedico(@RequestParam String email) {
        List<Usuario> usuarios = uS.buscarPacientesPorMedico(email);

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen pacientes del especialista con email: " + email);
        }

        List<PacienteListDTO> listaDTO = usuarios.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, PacienteListDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/totalPacientesPorEspecialista")
    public ResponseEntity<?> totalPacientesPorEspecialista(){
        List<String[]> total = uS.cantidadDePacientesPorEspecialista();
        List<TotalPacienteDTO> dtoList = new ArrayList<>();

        for (String[] columna : total) {
            TotalPacienteDTO dto = new TotalPacienteDTO();
            dto.setId(Integer.parseInt(columna[0]));
            dto.setNombre(columna[1]);
            dto.setApellido(columna[2]);
            dto.setEspecialidad(columna[3]);
            dto.setCantidadPacientes(Integer.parseInt(columna[4]));
            dtoList.add(dto);
        }
        return ResponseEntity.ok(dtoList);
    }
}

