package com.backend.portafolio.infrastructure.adapter.in.web;

import com.backend.portafolio.application.service.ExperienciaService;
import com.backend.portafolio.domain.model.Experiencia;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.ExperienciaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/experiencias")
@RequiredArgsConstructor
public class ExperienciaController {

    private final ExperienciaService experienciaService;

    @PostMapping
    public ResponseEntity<Experiencia> crearExperiencia(@RequestBody ExperienciaRequest request) {
        Experiencia experiencia = Experiencia.builder()
                .titulo(request.getTitulo())
                .institucion(request.getInstitucion())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .descripcion(request.getDescripcion())
                .build();
        return ResponseEntity.ok(experienciaService.crearExperiencia(experiencia));
    }

    @GetMapping
    public ResponseEntity<List<Experiencia>> obtenerMisExperiencias() {
        return ResponseEntity.ok(experienciaService.obtenerMisExperiencias());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experiencia> actualizarExperiencia(@PathVariable Long id, @RequestBody ExperienciaRequest request) {
        Experiencia experiencia = Experiencia.builder()
                .titulo(request.getTitulo())
                .institucion(request.getInstitucion())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .descripcion(request.getDescripcion())
                .build();
        return ResponseEntity.ok(experienciaService.actualizarExperiencia(id, experiencia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarExperiencia(@PathVariable Long id) {
        experienciaService.eliminarExperiencia(id);
        return ResponseEntity.noContent().build();
    }
}