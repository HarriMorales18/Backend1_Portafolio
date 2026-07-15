package com.backend.portafolio.infrastructure.adapter.in.web;

import com.backend.portafolio.application.service.ProyectoService;
import com.backend.portafolio.domain.model.Proyecto;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.ProyectoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proyectos")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService proyectoService;

    @PostMapping
    public ResponseEntity<Proyecto> crearProyecto(@RequestBody ProyectoRequest request) {
        Proyecto proyecto = Proyecto.builder()
                .titulo(request.getTitulo())
                .descripcion(request.getDescripcion())
                .urlImagen(request.getUrlImagen())
                .urlRepositorio(request.getUrlRepositorio())
                .urlDemo(request.getUrlDemo())
                .build();

        Proyecto proyectoGuardado = proyectoService.crearProyecto(proyecto);
        return ResponseEntity.ok(proyectoGuardado);
    }

    @GetMapping
    public ResponseEntity<List<Proyecto>> obtenerMisProyectos() {
        return ResponseEntity.ok(proyectoService.obtenerMisProyectos());
    }
}