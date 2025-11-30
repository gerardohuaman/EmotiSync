package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.*;
import com.neurobridge.emotisync.entities.Ejercicio;
import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.entities.Usuario_suscripcion;
import com.neurobridge.emotisync.repositories.IUsuarioRepository;
import com.neurobridge.emotisync.servicesinterfaces.IUsuario_suscripcionService;
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
@RequestMapping("/usuarioSuscripcion")
@PreAuthorize("isAuthenticated()")
public class Usuario_suscripcionController {
    @Autowired
    private IUsuario_suscripcionService uS;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario_suscripcionDTO> listar(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));

        List<Usuario_suscripcion> lista;

        if (isAdmin) {
            lista = uS.list();
        } else {
            lista = uS.listarPorUsuario(username); // Paciente ve solo SU suscripción
        }

        return lista.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            Usuario_suscripcionDTO dto  = m.map(x,Usuario_suscripcionDTO.class);
            if(x.getUsuario() != null){
                UsuarioListDTO usuarioDTO = m.map(x.getUsuario(), UsuarioListDTO.class);
                dto.setUsuario(usuarioDTO);
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<String> insertar(@RequestBody Usuario_suscripcion u) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 1. Buscar quién se está suscribiendo
        Usuario usuarioLogueado = usuarioRepository.findOneByUsername(auth.getName());
        if (usuarioLogueado == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        // 2. FORZAR ASIGNACIÓN: El usuario del token es el dueño de la suscripción
        u.setUsuario(usuarioLogueado);

        // 3. Lógica de Negocio (Opcional pero recomendada)
        // Por defecto, una nueva suscripción arranca hoy y está activa
        if (u.getFechaInicio() == null) u.setFechaInicio(LocalDate.now());
        // u.setEstado("Activo");

        uS.insert(u);
        return ResponseEntity.ok("Suscripción realizada con éxito");
    }

    @GetMapping("/usuarioActivoQuery")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<SuscripcionesActivasInfoUsuarioDTO> buscarActivos(){
        List<String[]> listString = uS.buscarActivos();
        List<SuscripcionesActivasInfoUsuarioDTO> dtoList = new ArrayList<>();

        for (String[] columna : listString) {
            SuscripcionesActivasInfoUsuarioDTO dto = new SuscripcionesActivasInfoUsuarioDTO();
            dto.setIdUsuarioSuscripcion(Integer.parseInt(columna[0]));
            dto.setIdUsuario(Integer.parseInt(columna[1]));
            dto.setEmail(columna[2]);
            dto.setIdPlanesSuscripcion(Integer.parseInt(columna[3]));
            dto.setNombrePlan(columna[4]);
            dto.setPrecio(Float.parseFloat(columna[5]));
            dtoList.add(dto);
        }
        return dtoList;
    }

    @GetMapping("/historialSuscripcionesQuery/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<HistorialSuscripcionesPorUsuarioDTO> buscarPorEmail(@PathVariable("email") String email){
        List<String[]> list = uS.buscarPorEmail(email);
        List<HistorialSuscripcionesPorUsuarioDTO> dtoList = new ArrayList<>();

        for (String[] columna : list) {
            HistorialSuscripcionesPorUsuarioDTO dto = new HistorialSuscripcionesPorUsuarioDTO();
            dto.setNombrePlan(columna[0]);
            dto.setPrecio(Float.parseFloat(columna[1]));
            dto.setEstado(columna[2]);
            dtoList.add(dto);
        }
        return dtoList;
    }



    @GetMapping("/planRendimientoQuery")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RendimientoPlanesDTO> buscarPlanRendimiento(){
        List<String[]> list = uS.buscarPorIdPlanesSuscripcion();
        List<RendimientoPlanesDTO> dtoList = new ArrayList<>();

        for (String[] columna : list){
            RendimientoPlanesDTO dto = new RendimientoPlanesDTO();
            dto.setIdPlanesSuscripcion(Integer.parseInt(columna[0]));
            dto.setNombrePlan(columna[1]);
            dto.setPrecio(Float.parseFloat(columna[2]));
            dto.setSuscriptoresActivos(Integer.parseInt(columna[3]));
            dto.setTotalHistorial(Integer.parseInt(columna[4]));
            dto.setPrecioTotalEstimado(Float.parseFloat(columna[5]));
            dtoList.add(dto);
        }
        return  dtoList;
    }

    @GetMapping("/MenosSuscriptoresQuery")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<MenosSuscriptoresActivosDTO> buscarMenosSuscriptoresActivos(){
        List<String[]> listS = uS.buscarPlanesMenosSuscriptoresActivos();
        List<MenosSuscriptoresActivosDTO> dtoList = new ArrayList<>();

        for (String[] columna : listS){
            MenosSuscriptoresActivosDTO dto = new MenosSuscriptoresActivosDTO();
            dto.setPlanId(Integer.parseInt(columna[0]));
            dto.setNombrePlan(columna[1]);
            dto.setPrecio(Float.parseFloat(columna[2]));
            dto.setSuscriptoresActivos(Integer.parseInt(columna[3]));
            dto.setTotalHistorial(Integer.parseInt(columna[4]));
            dto.setPorcentajeActivo(Float.parseFloat(columna[5]));
            dtoList.add(dto);
        }
        return dtoList;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificar(@RequestBody Usuario_suscripcionDTO dto) {
        ModelMapper m = new ModelMapper();
        Usuario_suscripcion userSus = m.map(dto, Usuario_suscripcion.class);
        Usuario_suscripcion existente = uS.listId(userSus.getIdUsuarioSuscripcion());

        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe el registro ID: " + userSus.getIdUsuarioSuscripcion());
        }

        // Mantener el usuario original para evitar que el admin lo reasigne por error
        userSus.setUsuario(existente.getUsuario());

        uS.update(userSus);
        return ResponseEntity.ok("Registro modificado correctamente.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Usuario_suscripcion s = uS.listId(id);
        if (s == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe");

        // Validación: Solo el dueño o admin pueden ver el detalle
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));

        if (!isAdmin && !s.getUsuario().getUsername().equals(auth.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso");
        }

        ModelMapper m = new ModelMapper();
        Usuario_suscripcionDTO dto = m.map(s, Usuario_suscripcionDTO.class);
        return ResponseEntity.ok(dto);
    }
}
