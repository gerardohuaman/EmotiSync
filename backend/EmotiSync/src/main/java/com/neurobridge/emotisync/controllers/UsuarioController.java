package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.PacienteListDTO;
import com.neurobridge.emotisync.dtos.TotalPacienteDTO;
import com.neurobridge.emotisync.dtos.UsuarioInsertDTO;
import com.neurobridge.emotisync.dtos.UsuarioListDTO;
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
public class UsuarioController {
    @Autowired
    private IUsuarioService uS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UsuarioListDTO>listarUsuarios(){
        return uS.getUsuarios().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioListDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/pacientes")
    @PreAuthorize("hasRole('ADMIN')")
    public List<PacienteListDTO>listarPaciente(){
        return uS.buscarPacientesService().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, PacienteListDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void insertar(@RequestBody UsuarioInsertDTO u){
        ModelMapper m = new ModelMapper();
        Usuario usuario=m.map(u, Usuario.class);
        uS.insert(usuario);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Usuario usuario = uS.listId(id);
        if (usuario == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        UsuarioListDTO dto = m.map(usuario, UsuarioListDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
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

    @GetMapping("/familiar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> buscarFamiliarPorPaciente(@RequestParam int familiarDe) {
        Usuario usuario = uS.buscarFamiliarPorPacienteService(familiarDe);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un familiar para el paciente con Id: " + familiarDe);
        }
        ModelMapper m = new ModelMapper();
        UsuarioListDTO usuarioListDTO = m.map(usuario, UsuarioListDTO.class);

        return ResponseEntity.ok(usuarioListDTO);
    }


    @GetMapping("/pacientesDe")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> buscarPacientePorMedico(@RequestParam int especialistaId) {
        List<Usuario> usuarios = uS.buscarPacientesPorMedico(especialistaId);

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen pacientes del especialista con id: " + especialistaId);
        }

        List<PacienteListDTO> listaDTO = usuarios.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, PacienteListDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("totalPacientesPorEspecialista")
    @PreAuthorize("hasAuthority('ADMIN')")
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

