package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.DiarioListDTO;
import com.neurobridge.emotisync.dtos.DiarioDTOInsert;
import com.neurobridge.emotisync.entities.Diario;
import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.repositories.IUsuarioRepository;
import com.neurobridge.emotisync.servicesinterfaces.IDiarioService;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/diarios")
@PreAuthorize("isAuthenticated()")
public class DiarioController {
    @Autowired
    private IDiarioService diarioService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @GetMapping
    public List<DiarioListDTO> listar(){
        //Obtiene usuario logueado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        //Verifica el rol
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));

        List<Diario> lista;

        //Logica de filtro
        if(isAdmin){
            lista = diarioService.list(); //Administrador ve todo
        } else {
            lista = diarioService.listarPorUsuario(username); //Paciente solo ve lo suyo
        }

        return diarioService.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, DiarioListDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<String> insertar(@RequestBody DiarioDTOInsert u){
        // Obtiene usuario real del token
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogueado = usuarioRepository.findOneByUsername(auth.getName());

        if(usuarioLogueado == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }
        ModelMapper m = new ModelMapper();
        Diario diario=m.map(u, Diario.class);

        diario.setUsuario(usuarioLogueado);

        diarioService.insert(diario);
        return ResponseEntity.ok("Diario guardado correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable("id") Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));

        Diario r = diarioService.listId(id);

        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        if(!isAdmin && !r.getUsuario().getUsername().equals(username)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para ver este diario");
        }

        ModelMapper m = new ModelMapper();
        DiarioListDTO dto = m.map(r, DiarioListDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Diario diario = diarioService.listId(id);
        if (diario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        if(!diario.getUsuario().getUsername().equals(username) &&
            auth.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No puedes eliminar este diario");
        }
        diarioService.delete(id);
        return ResponseEntity.ok("Eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody DiarioDTOInsert dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Diario existente = diarioService.listId(dto.getIdDiario());
        if(existente == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe");

        if(!existente.getUsuario().getUsername().equals(auth.getName())){
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("No puedes editar este diario");
        }

        ModelMapper m = new ModelMapper();
        Diario s = m.map(dto, Diario.class);
        s.setUsuario(existente.getUsuario());

        diarioService.update(s);
        return ResponseEntity.ok("Modificado correctamente.");
    }

}
