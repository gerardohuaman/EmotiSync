package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.HistorialSuscripcionesPorUsuarioDTO;
import com.neurobridge.emotisync.dtos.RendimientoPlanesDTO;
import com.neurobridge.emotisync.dtos.SuscripcionesActivasInfoUsuarioDTO;
import com.neurobridge.emotisync.dtos.Usuario_suscripcionDTO;
import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.servicesinterfaces.IUsuario_suscripcionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
