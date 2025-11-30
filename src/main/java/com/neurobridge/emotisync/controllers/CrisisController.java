package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.CrisisDTO;
import com.neurobridge.emotisync.dtos.QuantityDTOCrisis;
import com.neurobridge.emotisync.entities.Crisis;
import com.neurobridge.emotisync.entities.Emociones;
import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.repositories.IUsuarioRepository;
import com.neurobridge.emotisync.servicesinterfaces.ICrisisService;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/crisis")
@PreAuthorize("isAuthenticated()")
public class CrisisController {
    @Autowired
    private ICrisisService crisisService;

    //Repositorio para buscar al usuario real
    @Autowired
    private IUsuarioRepository usuarioRepository;

    //read
    @GetMapping
    public List<CrisisDTO> listar(){
        // 1. Detectar quién es
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));

        List<Crisis> lista;

        // 2. Filtrar datos
        if (isAdmin) {
            lista = crisisService.list();
        } else {
            lista = crisisService.listarPorUsuario(username);
        }

        return lista.stream().map( x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, CrisisDTO.class);
        }).collect(Collectors.toList());
    }

    //create
    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody CrisisDTO crisisDTO){
        // 1. Obtener usuario real
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogueado = usuarioRepository.findOneByUsername(auth.getName());

        if(usuarioLogueado == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        ModelMapper m = new ModelMapper();
        Crisis cri = m.map(crisisDTO, Crisis.class);

        // 2. Asignación Forzosa (Seguridad)
        cri.setUsuario(usuarioLogueado);
        cri.setIdCrisis(0); // Reset ID por si acaso

        crisisService.insert(cri);
        return ResponseEntity.ok("Crisis registrada correctamente");
    }

    //update
    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody CrisisDTO crisisDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Crisis existente = crisisService.listId(crisisDTO.getIdCrisis());
        if(existente == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe");

        if(!existente.getUsuario().getUsername().equals(auth.getName())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No puedes editar esta crisis");
        }

        ModelMapper m = new ModelMapper();
        Crisis cri = m.map(crisisDTO, Crisis.class);
        cri.setUsuario(existente.getUsuario());

        crisisService.update(cri);
        return ResponseEntity.ok( "Registro con ID " + cri.getIdCrisis() + " modificado correctamente.");
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));

        Crisis cri = crisisService.listId(id);
        if (cri == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe");
        }

        // Validación de dueño
        if (!isAdmin && !cri.getUsuario().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso");
        }

        crisisService.delete(id);
        return ResponseEntity.ok("Eliminado correctamente.");
    }

    //queries
    @GetMapping("/buscarporritmo")
    public ResponseEntity<?> buscar(@RequestParam float ritmo ){
        List<Crisis> crisis= crisisService.buscarPorRitmo(ritmo);
        if(crisis.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron crisis para el usuario con ID: " + ritmo);
        }
        List<CrisisDTO> listaDTO = crisis.stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, CrisisDTO.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/buscarporusurangofechas")
    public ResponseEntity<?> buscarPorUsuario(@RequestParam Integer id,
                                              @RequestParam LocalDate fechaInicio,
                                              @RequestParam LocalDate fechaFin){
        List<Crisis> crisis = crisisService.buscarPorUsuarioYRangoFechas(id, fechaInicio, fechaFin);
        if(crisis.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron crisis en el rango de fechas: "
                            + fechaInicio + " - " + fechaFin);
        }
        List<CrisisDTO> listaDTO = crisis.stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, CrisisDTO.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/cantidadporusu")
    public ResponseEntity<?> cantidadPorUsuario(){
        List<QuantityDTOCrisis> listaDTO = new ArrayList<>();
        List<String[]> fila = crisisService.cantidadCrisisDelUsuario();
        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros" );
        }
        for(String[] columna:fila){
            QuantityDTOCrisis dto=new QuantityDTOCrisis();
            dto.setIdCrisis(Integer.parseInt(columna[0]));
            dto.setQuantityCrisis(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));

        Crisis cri = crisisService.listId(id);
        if (cri == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe crisis con el ID: " + id);
        }

        if(!isAdmin && !cri.getUsuario().getUsername().equals(username)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para ver esta crisis");
        }
        ModelMapper m = new ModelMapper();
        CrisisDTO dto = m.map(cri, CrisisDTO.class);
        return ResponseEntity.ok(dto);
    }
}
