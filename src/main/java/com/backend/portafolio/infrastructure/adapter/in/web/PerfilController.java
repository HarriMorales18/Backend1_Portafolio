package com.backend.portafolio.infrastructure.adapter.in.web;

import com.backend.portafolio.application.service.PerfilService;
import com.backend.portafolio.domain.model.Perfil;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.PerfilRequest;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.PerfilResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/perfil")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;

    @GetMapping
    public ResponseEntity<Perfil> obtenerMiPerfil() {
        return ResponseEntity.ok(perfilService.obtenerMiPerfil());
    }

    @PostMapping
    public ResponseEntity<Perfil> crearPerfil(@RequestBody PerfilRequest request) {
        return ResponseEntity.ok(perfilService.guardarOActualizarPerfil(mapearDesdeRequest(request)));
    }

    @PutMapping
    public ResponseEntity<Perfil> actualizarPerfil(@RequestBody PerfilRequest request) {
        return ResponseEntity.ok(perfilService.guardarOActualizarPerfil(mapearDesdeRequest(request)));
    }

    private Perfil mapearDesdeRequest(PerfilRequest request) {
        return Perfil.builder()
                .nombreCompleto(request.getNombreCompleto())
                .titular(request.getTitular())
                .biografia(request.getBiografia())
                .urlLinkedin(request.getUrlLinkedin())
                .urlGithub(request.getUrlGithub())
                .urlFotoPerfil(request.getUrlFotoPerfil())
                .build();
    }

    @PostMapping(value = "/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Perfil> subirFotoPerfil(@RequestParam("file") MultipartFile file) {
        Perfil perfilActualizado = perfilService.subirFotoPerfil(file);
        return ResponseEntity.ok(perfilActualizado);
    }
}