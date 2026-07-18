package com.backend.portafolio.infrastructure.adapter.in.web;

import com.backend.portafolio.application.service.PortafolioPublicoService;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.PortafolioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/publico/portafolio")
@RequiredArgsConstructor
public class PortafolioPublicoController {

    private final PortafolioPublicoService portafolioPublicoService;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<PortafolioResponse> obtenerPortafolio(@PathVariable Long usuarioId) {
        PortafolioResponse respuesta = portafolioPublicoService.obtenerPortafolioCompleto(usuarioId);

        if (respuesta.getPerfil() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(respuesta);
    }
}