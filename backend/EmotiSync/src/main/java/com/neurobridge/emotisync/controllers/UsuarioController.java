package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.UsuarioDTO;
import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.servicesinterfaces.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioService uS;

    @GetMapping
    public List<UsuarioDTO>listar(){
        return uS.getUsuarios().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,UsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody UsuarioDTO u){
        ModelMapper m = new ModelMapper();
        Usuario usuario=m.map(u, Usuario.class);
        uS.insert(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Usuario usuario = uS.listId(id);
        if (usuario == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        UsuarioDTO dto = m.map(usuario, UsuarioDTO.class);
        return ResponseEntity.ok(dto);
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
    public ResponseEntity<String> modificar(@RequestBody UsuarioDTO dto) {
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
    public ResponseEntity<?> buscarFamiliarPorPaciente(@RequestParam int familiarDe) {
        Usuario usuario = uS.buscarFamiliarPorPacienteService(familiarDe);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un familiar para el paciente con Id: " + familiarDe);
        }
        ModelMapper m = new ModelMapper();
        UsuarioDTO usuarioDTO = m.map(usuario, UsuarioDTO.class);

        return ResponseEntity.ok(usuarioDTO);
    }

}

