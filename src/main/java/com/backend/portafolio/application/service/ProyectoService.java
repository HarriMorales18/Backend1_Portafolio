package com.backend.portafolio.application.service;

import com.backend.portafolio.domain.model.Proyecto;
import com.backend.portafolio.domain.model.Usuario;
import com.backend.portafolio.domain.port.out.ProyectoRepositoryPort;
import com.backend.portafolio.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectoService {

    private final ProyectoRepositoryPort proyectoRepositoryPort;
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public Proyecto crearProyecto(Proyecto proyecto) {
        String correoUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario usuario = usuarioRepositoryPort.findByCorreo(correoUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        proyecto.setUsuarioId(usuario.getId());
        proyecto.setFechaCreacion(LocalDateTime.now());

        return proyectoRepositoryPort.save(proyecto);
    }

    public List<Proyecto> obtenerMisProyectos() {
        String correoUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepositoryPort.findByCorreo(correoUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return proyectoRepositoryPort.findAllByUsuarioId(usuario.getId());
    }
}