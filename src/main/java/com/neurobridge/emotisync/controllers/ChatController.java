package com.neurobridge.emotisync.controllers;

import com.neurobridge.emotisync.dtos.ChatDTO;
import com.neurobridge.emotisync.servicesimplements.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@PreAuthorize("isAuthenticated()")
public class ChatController {
    @Autowired
    private GeminiService geminiService;

    @PostMapping
    public ResponseEntity<ChatDTO> chatear(@RequestBody ChatDTO solicitud) {
        String respuestaIA = geminiService.obtenerRespuesta(solicitud.getMensaje());
        return ResponseEntity.ok(new ChatDTO(solicitud.getMensaje(), respuestaIA));
    }
}
