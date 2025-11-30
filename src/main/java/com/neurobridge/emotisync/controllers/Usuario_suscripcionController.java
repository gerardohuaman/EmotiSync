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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarioSuscripcion")
@PreAuthorize("hasAuthority('ADMIN')")
public class Usuario_suscripcionController {
    @Autowired
    private IUsuario_suscripcionService uS;

    @GetMapping
    public List<Usuario_suscripcionDTO> listar(){
        return uS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            Usuario_suscripcionDTO dto  = m.map(x,Usuario_suscripcionDTO.class);

            if(x.getUsuario() != null){
                UsuarioListDTO usuarioDTO = m.map(x.getUsuario(),UsuarioListDTO.class);
                dto.setUsuario(usuarioDTO);
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody Usuario_suscripcion u) {
        ModelMapper m = new ModelMapper();
        Usuario_suscripcionDTO dto = m.map(u, Usuario_suscripcionDTO.class);
        uS.insert(u);
    }

    @GetMapping("/usuarioActivoQuery")
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
    public ResponseEntity<String> modificar(@RequestBody Usuario_suscripcionDTO dto) {
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

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Usuario_suscripcion s = uS.listId(id);
        if (s == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        Usuario_suscripcionDTO dto = m.map(s, Usuario_suscripcionDTO.class);
        return ResponseEntity.ok(dto);
    }
}
