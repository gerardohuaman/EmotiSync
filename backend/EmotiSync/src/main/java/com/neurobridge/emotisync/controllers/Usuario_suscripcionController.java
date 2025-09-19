package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.Usuario_suscripcionDTO;
import com.neurobridge.emotisync.servicesinterfaces.IUsuario_suscripcionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<Usuario_suscripcionDTO> listar(){
        return uS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, Usuario_suscripcionDTO.class);
        }).collect(Collectors.toList());
    }
}
