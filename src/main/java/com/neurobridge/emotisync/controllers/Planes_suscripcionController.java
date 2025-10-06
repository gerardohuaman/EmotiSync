package com.neurobridge.emotisync.controllers;

import org.modelmapper.ModelMapper;
import com.neurobridge.emotisync.dtos.Planes_suscripcionDTO;
import com.neurobridge.emotisync.servicesinterfaces.IPlanes_suscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/planesSuscripcion")
public class Planes_suscripcionController {
    @Autowired
    private IPlanes_suscripcionService pS;

    @GetMapping
    public List<Planes_suscripcionDTO> listar(){
        return pS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,Planes_suscripcionDTO.class);
        }).collect(Collectors.toList());
    }
}
