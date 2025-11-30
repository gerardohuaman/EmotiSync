package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.RecursoPromedioDTO;
import com.neurobridge.emotisync.dtos.RecursoRelacionDTO;
import com.neurobridge.emotisync.repositories.IUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.neurobridge.emotisync.dtos.RecursoDTO;
import com.neurobridge.emotisync.entities.Recurso;
import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.servicesinterfaces.IRecursoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recursos")
@PreAuthorize("isAuthenticated()")
public class RecursoController {
    @Autowired
    private IRecursoService rService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @GetMapping
    public List<RecursoDTO> listar() {
        // 1. Seguridad
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));

        List<Recurso> lista;

        // 2. Filtro
        if (isAdmin) {
            lista = rService.list();
        } else {
            // Paciente ve solo SUS grabaciones
            lista = rService.listarPorCreador(username);
        }

        return lista.stream().map(r -> {
            ModelMapper m = new ModelMapper();
            RecursoDTO dto = m.map(r, RecursoDTO.class);

            // Mapeo manual de IDs para evitar nulos
            if(r.getCreador() != null) {
                dto.setCreadorId(r.getCreador().getIdUsuario());
                dto.setNombreCreador(r.getCreador().getUsername());
            }
            if(r.getDestinatario() != null) {
                dto.setDestinatarioId(r.getDestinatario().getIdUsuario());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));

        Recurso r = rService.listId(id);
        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe");
        }

        // Validación de propiedad
        if (!isAdmin && !r.getCreador().getUsername().equals(username)) {
            // Opcional: Permitir si soy el 'destinatario' (ej. mi médico)
            if (r.getDestinatario() == null || !r.getDestinatario().getUsername().equals(username)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes acceso a este recurso");
            }
        }

        ModelMapper m = new ModelMapper();
        RecursoDTO dto = m.map(r, RecursoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<String> insertar(@RequestBody RecursoDTO dto) {
        // 1. Obtener usuario real
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario creadorReal = usuarioRepository.findOneByUsername(auth.getName());

        if (creadorReal == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        ModelMapper m = new ModelMapper();
        Recurso r = m.map(dto, Recurso.class);
        r.setId(null);

        // 2. ASIGNACIÓN SEGURA DEL CREADOR
        // Ignoramos el 'creadorId' que envía el frontend y usamos el del token
        r.setCreador(creadorReal);

        // 3. Asignar Destinatario (si se envía)
        // Esto sí permitimos que venga del DTO (ejemplo, "quiero enviar esto a mi médico X")
        if(dto.getDestinatarioId() != null){
            Usuario dest = new Usuario();
            dest.setIdUsuario(dto.getDestinatarioId());
            r.setDestinatario(dest);
        }


        rService.insert(r);
        return ResponseEntity.status(HttpStatus.CREATED).body("Recurso creado correctamente");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody RecursoDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Recurso existente = rService.listId(dto.getId());

        if (existente == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        // Validar propiedad
        if (!existente.getCreador().getUsername().equals(auth.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        ModelMapper m = new ModelMapper();
        Recurso r = m.map(dto, Recurso.class);
        r.setCreador(existente.getCreador()); // No cambiar de dueño

        rService.update(r);
        return ResponseEntity.ok("Modificado");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));

        Recurso r = rService.listId(id);
        if (r == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");

        // Solo el dueño o admin borran
        if (!isAdmin && !r.getCreador().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No puedes eliminar este recurso");
        }

        rService.delete(id);
        return ResponseEntity.ok("Recurso eliminado correctamente");
    }

    @GetMapping("/relacion")
    public ResponseEntity<RecursoRelacionDTO> verificarRelacion(
            @RequestParam int creadorId,
            @RequestParam int destinatarioId) {

        boolean existe = rService.existeRelacionEntreUsuarios(creadorId, destinatarioId);

        RecursoRelacionDTO dto = new RecursoRelacionDTO();
        dto.setCreadorId(creadorId);
        dto.setDestinatarioId(destinatarioId);
        dto.setExisteRelacion(existe);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/promedio")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<RecursoPromedioDTO>> promedioRecursos() {
        List<RecursoPromedioDTO> lista = rService.promedioRecursosPorCreador();
        return ResponseEntity.ok(lista);
    }


}