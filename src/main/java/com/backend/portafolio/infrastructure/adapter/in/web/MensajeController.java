package com.backend.portafolio.infrastructure.adapter.in.web;

import com.backend.portafolio.application.service.MensajeService;
import com.backend.portafolio.domain.model.Mensaje;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mensajes")
@RequiredArgsConstructor
public class MensajeController {

    private final MensajeService mensajeService;

    @GetMapping
    public ResponseEntity<List<Mensaje>> obtenerMisMensajes() {
        return ResponseEntity.ok(mensajeService.obtenerMisMensajes());
    }
}