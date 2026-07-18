package com.backend.portafolio.infrastructure.adapter.in.web;

import com.backend.portafolio.application.service.HabilidadService;
import com.backend.portafolio.domain.model.Habilidad;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.HabilidadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/habilidades")
@RequiredArgsConstructor
public class HabilidadController {

    private final HabilidadService habilidadService;

    @PostMapping
    public ResponseEntity<Habilidad> crearHabilidad(@RequestBody HabilidadRequest request) {
        Habilidad habilidad = Habilidad.builder()
                .nombre(request.getNombre())
                .porcentaje(request.getPorcentaje())
                .build();
        return ResponseEntity.ok(habilidadService.crearHabilidad(habilidad));
    }

    @GetMapping
    public ResponseEntity<List<Habilidad>> obtenerMisHabilidades() {
        return ResponseEntity.ok(habilidadService.obtenerMisHabilidades());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habilidad> actualizarHabilidad(@PathVariable Long id, @RequestBody HabilidadRequest request) {
        Habilidad habilidad = Habilidad.builder()
                .nombre(request.getNombre())
                .porcentaje(request.getPorcentaje())
                .build();
        return ResponseEntity.ok(habilidadService.actualizarHabilidad(id, habilidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHabilidad(@PathVariable Long id) {
        habilidadService.eliminarHabilidad(id);
        return ResponseEntity.noContent().build();
    }
}