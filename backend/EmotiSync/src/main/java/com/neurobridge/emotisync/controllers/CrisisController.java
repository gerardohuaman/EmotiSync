package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.CrisisDTO;
import com.neurobridge.emotisync.entities.Crisis;
import com.neurobridge.emotisync.entities.Emociones;
import com.neurobridge.emotisync.servicesinterfaces.ICrisisService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/crisis")
public class CrisisController {
    @Autowired
    private ICrisisService crisisService;

    //read
    @GetMapping
    public List<CrisisDTO> listar(){
        return crisisService.list().stream().map( x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, CrisisDTO.class);
        }).collect(Collectors.toList());
    }

    //create
    @PostMapping
    public void insertar(@RequestBody CrisisDTO crisisDTO){
        ModelMapper m = new ModelMapper();
        Crisis cri = m.map(crisisDTO, Crisis.class);
        crisisService.insert(cri);
    }

    //update
    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody CrisisDTO crisisDTO){
        ModelMapper m = new ModelMapper();
        Crisis cri = m.map(crisisDTO, Crisis.class);
        Crisis existente = crisisService.listId(cri.getIdCrisis());
        if(existente != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo actualizar/modificar el registro con el ID: " + cri.getIdCrisis());
        }
        crisisService.update(cri);
        return ResponseEntity.ok( "Registro con ID " + cri.getIdCrisis() + " modificado correctamente.");
    }

    //delete
    @DeleteMapping("/{id}")

    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        Crisis cri = crisisService.listId(id);
        if (cri == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe el registro con el ID: " + id);
        }
        crisisService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    //queries
    @GetMapping("/buscarporusu")
    public ResponseEntity<?> buscar(@RequestParam int id ){
        List<Crisis> crisis= crisisService.buscarPorUsuario(id);
        if(crisis.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron crisis para el usuario con ID: " + id);
        }
        List<CrisisDTO> listaDTO = crisis.stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, CrisisDTO.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/buscarporusurangofechas")
    public ResponseEntity<?> buscarPorUsuario(@RequestParam Integer id, @RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin){
        List<Crisis> crisis = crisisService.buscarPorUsuarioYRangoFechas(id, fechaInicio, fechaFin);
        if(crisis.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron crisis en el rango de fechas: " + fechaInicio + " - " + fechaFin);
        }
        List<CrisisDTO> listaDTO = crisis.stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, CrisisDTO.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

//    @GetMapping("/cantidadporusu")
//    public ResponseEntity<?> cantidadPorUsuario(){
//        List<String[]> fila=crisisService.cantidadCrisisDelUsuario();
//        List<>
//    }

}
