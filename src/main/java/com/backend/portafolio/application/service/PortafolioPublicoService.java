package com.backend.portafolio.application.service;

import com.backend.portafolio.domain.port.out.ExperienciaRepositoryPort;
import com.backend.portafolio.domain.port.out.HabilidadRepositoryPort;
import com.backend.portafolio.domain.port.out.PerfilRepositoryPort;
import com.backend.portafolio.domain.port.out.ProyectoRepositoryPort;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.PortafolioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortafolioPublicoService {

    private final PerfilRepositoryPort perfilRepositoryPort;
    private final ProyectoRepositoryPort proyectoRepositoryPort;
    private final HabilidadRepositoryPort habilidadRepositoryPort;
    private final ExperienciaRepositoryPort experienciaRepositoryPort;

    public PortafolioResponse obtenerPortafolioCompleto(Long usuarioId) {

        return PortafolioResponse.builder()
                .perfil(perfilRepositoryPort.findByUsuarioId(usuarioId).orElse(null))
                .proyectos(proyectoRepositoryPort.findAllByUsuarioId(usuarioId))
                .habilidades(habilidadRepositoryPort.findAllByUsuarioId(usuarioId))
                .experiencias(experienciaRepositoryPort.findAllByUsuarioId(usuarioId))
                .build();
    }
}