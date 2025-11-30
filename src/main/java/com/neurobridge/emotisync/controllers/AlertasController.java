package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.*;
import com.neurobridge.emotisync.entities.Ejercicio;
import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.repositories.IUsuarioRepository;
import com.neurobridge.emotisync.servicesinterfaces.IAlertaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.neurobridge.emotisync.entities.Alertas;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alertas")
@PreAuthorize("isAuthenticated()")
public class AlertasController {
    @Autowired
    private IAlertaService service;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @GetMapping
    public List<AlertasDTOList> listar() {
        // 1. Seguridad
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Permitimos que Admin, Especialista y Familiar vean más datos
        boolean isStaff = auth.getAuthorities().stream().anyMatch(a ->
                a.getAuthority().equals("ADMIN") ||
                        a.getAuthority().equals("ESPECIALISTA") ||
                        a.getAuthority().equals("FAMILIAR"));

        List<Alertas> lista;

        // 2. Filtro
        if (isStaff) {
            lista = service.list(); // Ven todo (o deberían filtrar por sus pacientes)
        } else {
            lista = service.listarPorUsuario(username); // Paciente ve solo SUS alertas
        }

        return lista.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, AlertasDTOList.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody AlertasDTOInsert lic) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogueado = usuarioRepository.findOneByUsername(auth.getName());

        ModelMapper m = new ModelMapper();
        Alertas l = m.map(lic, Alertas.class);

        // Asignar usuario logueado
        l.setUsuario(usuarioLogueado);

        service.insert(l);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Alertas alerta = service.listId(id);
        if (alerta == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe");

        // Validación
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isStaff = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN") || a.getAuthority().equals("ESPECIALISTA"));

        if (!isStaff && !alerta.getUsuario().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        }

        ModelMapper m = new ModelMapper();
        AlertasDTOList dto = m.map(alerta, AlertasDTOList.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Alertas alerta = service.listId(id);
        if (alerta == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe");
        service.delete(id);
        return ResponseEntity.ok("Eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody AlertasDTOList dto) {
        ModelMapper m = new ModelMapper();
        Alertas s = m.map(dto, Alertas.class);
        Alertas existente = service.listId(s.getIdAlerta());

        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe...");
        }

        // --- NUEVA VALIDACIÓN DE SEGURIDAD ---
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isStaff = auth.getAuthorities().stream().anyMatch(a ->
                a.getAuthority().equals("ADMIN") || a.getAuthority().equals("ESPECIALISTA"));

        // Si no es Staff Y la alerta no le pertenece -> Bloquear
        if (!isStaff && !existente.getUsuario().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No puedes modificar alertas de otros usuarios");
        }
        // -------------------------------------

        // Mantener el usuario original para evitar que se pierda o cambie accidentalmente
        s.setUsuario(existente.getUsuario());

        service.update(s);
        return ResponseEntity.ok("Modificado correctamente.");

    }

    @GetMapping("/conteo-usuarios")
    @PreAuthorize("hasAuthority('ADMIN', 'ESPECIALISTA')")
    public ResponseEntity<List<UsuarioAlertaCountDTO>> contarAlertasPorUsuario() {
        List<UsuarioAlertaCountDTO> conteo = service.contarAlertasPorUsuario();

        if (conteo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(conteo);
    }

    @GetMapping("/buscar-por-nombre")
    @PreAuthorize("hasAuthority('ADMIN', 'ESPECIALISTA')")
    public ResponseEntity<List<AlertasBusquedaDTO>> buscarAlertasPorNombreUsuario(
            @RequestParam("letra") String letra) {

        List<AlertasBusquedaDTO> alertas = service.buscarAlertasPorNombreUsuario(letra);

        if (alertas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(alertas);
    }

    // DECISIÓN: Usuarios cuyo promedio de alertas >= nivel crítico
    @GetMapping("/promedio-critico")
    @PreAuthorize("hasAuthority('ADMIN', 'ESPECIALISTA')")
    public ResponseEntity<List<UsuarioPromedioAlertasDTO>> usuariosConPromedioAlertasAltas(
            @RequestParam("nivel") double nivel) {

        List<UsuarioPromedioAlertasDTO> resultado = service.usuariosConPromedioAlertasAltas(nivel);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(resultado);
    }

    //Detecta a los usuarios cuyo nivel máximo de alerta es crítico
    @GetMapping("/criticas")
    @PreAuthorize("hasAuthority('ADMIN', 'ESPECIALISTA')")
    public ResponseEntity<List<UsuarioAlertaDTO>> obtenerUsuariosConAlertasCriticas(
            @RequestParam(defaultValue = "4") int nivel) {
        List<UsuarioAlertaDTO> lista = service.obtenerUsuariosConAlertasCriticas(nivel);

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(lista);

    }
}
