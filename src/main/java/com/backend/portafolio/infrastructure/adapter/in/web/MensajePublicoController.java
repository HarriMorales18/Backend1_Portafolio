package com.backend.portafolio.infrastructure.adapter.in.web;

import com.backend.portafolio.application.service.MensajeService;
import com.backend.portafolio.domain.model.Mensaje;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.MensajeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/publico/mensajes")
@RequiredArgsConstructor
public class MensajePublicoController {

    private final MensajeService mensajeService;

    @PostMapping("/{usuarioId}")
    public ResponseEntity<String> enviarMensaje(@PathVariable Long usuarioId, @RequestBody MensajeRequest request) {
        Mensaje mensaje = Mensaje.builder()
                .nombreRemitente(request.getNombreRemitente())
                .correoRemitente(request.getCorreoRemitente())
                .contenido(request.getContenido())
                .build();

        mensajeService.enviarMensaje(usuarioId, mensaje);
        return ResponseEntity.ok("Mensaje enviado con éxito");
    }
}