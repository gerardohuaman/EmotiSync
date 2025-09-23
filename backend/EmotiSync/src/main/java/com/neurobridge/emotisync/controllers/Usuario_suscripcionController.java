package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.*;
import com.neurobridge.emotisync.entities.Ejercicio;
import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.entities.Usuario_suscripcion;
import com.neurobridge.emotisync.servicesinterfaces.IUsuario_suscripcionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarioSuscripcion")
public class Usuario_suscripcionController {
    @Autowired
    private IUsuario_suscripcionService uS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Usuario_suscripcionDTO> listar(){
        return uS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, Usuario_suscripcionDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/usuarioActivo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<SuscripcionesActivasInfoUsuarioDTO> buscarActivos(){
        return uS.buscarActivos().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, SuscripcionesActivasInfoUsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> buscarPorEmail(@PathVariable("id") Integer id){
        Usuario usuario = (Usuario) uS.buscarPorEmail(id);
        if (usuario == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        HistorialSuscripcionesPorUsuarioDTO dto = m.map(usuario, HistorialSuscripcionesPorUsuarioDTO.class);
        return ResponseEntity.ok(dto);
    }
    
    @GetMapping("/planRendimiento")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RendimientoPlanesDTO> buscarPlanRendimiento(){
        return uS.buscarPorIdPlanesSuscripcion().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, RendimientoPlanesDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Usuario_suscripcion usuario_suscripcion = uS.listId(id);
        if (usuario_suscripcion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        uS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody EjercicioDTO dto) {
        ModelMapper m = new ModelMapper();
        Usuario_suscripcion userSus = m.map(dto, Usuario_suscripcion.class);
        Usuario_suscripcion existente = uS.listId(userSus.getIdUsuarioSuscripcion());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + userSus.getIdUsuarioSuscripcion());
        }
        uS.update(userSus);
        return ResponseEntity.ok("Registro con ID " + userSus.getIdUsuarioSuscripcion() + " modificado correctamente.");
    }
}
