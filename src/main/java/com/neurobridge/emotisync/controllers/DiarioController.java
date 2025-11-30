package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.DiarioListDTO;
import com.neurobridge.emotisync.dtos.DiarioDTOInsert;
import com.neurobridge.emotisync.entities.Diario;
import com.neurobridge.emotisync.entities.Emociones;
import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.servicesinterfaces.IDiarioService;
import com.neurobridge.emotisync.servicesinterfaces.IEmocionesService;
import com.neurobridge.emotisync.servicesinterfaces.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/diarios")
public class DiarioController {
    @Autowired
    private IDiarioService diarioService;

    @Autowired
    private IEmocionesService emocionesService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public List<DiarioListDTO> listar(){
        return diarioService.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, DiarioListDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody DiarioDTOInsert u){

        Emociones e = emocionesService.listId(u.getEmociones());
        Usuario user = usuarioService.listId(u.getUsuario());
        ModelMapper m = new ModelMapper();
        Diario diario=m.map(u, Diario.class);
        diario.setEmociones(e);
        diario.setUsuario(user);
        System.out.println(diario);
        diarioService.insert(diario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable("id") Integer id) {
        Diario r = diarioService.listId(id);

        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        DiarioListDTO dto = m.map(r, DiarioListDTO.class);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Diario diario = diarioService.listId(id);
        if (diario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        diarioService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody DiarioDTOInsert dto) {

        Emociones e = emocionesService.listId(dto.getEmociones());
        Usuario user = usuarioService.listId(dto.getUsuario());
        ModelMapper m = new ModelMapper();
        Diario s = m.map(dto, Diario.class);
        Diario existente = diarioService.listId(s.getIdDiario());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + s.getIdDiario());
        }
        s.setEmociones(e);
        s.setUsuario(user);
        diarioService.update(s);
        return ResponseEntity.ok("Registro con ID " + s.getIdDiario() + " modificado correctamente.");
    }

}
