package com.backend.portafolio.application.service;

import com.backend.portafolio.domain.model.Habilidad;
import com.backend.portafolio.domain.model.Usuario;
import com.backend.portafolio.domain.port.out.HabilidadRepositoryPort;
import com.backend.portafolio.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabilidadService {

    private final HabilidadRepositoryPort habilidadRepositoryPort;
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    private Usuario obtenerUsuarioAutenticado() {
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepositoryPort.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Habilidad crearHabilidad(Habilidad habilidad) {
        Usuario usuario = obtenerUsuarioAutenticado();
        habilidad.setUsuarioId(usuario.getId());
        return habilidadRepositoryPort.save(habilidad);
    }

    public List<Habilidad> obtenerMisHabilidades() {
        Usuario usuario = obtenerUsuarioAutenticado();
        return habilidadRepositoryPort.findAllByUsuarioId(usuario.getId());
    }

    public Habilidad actualizarHabilidad(Long id, Habilidad habilidadActualizada) {
        Usuario usuarioActual = obtenerUsuarioAutenticado();
        Habilidad habilidadExistente = habilidadRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Habilidad no encontrada"));

        if (!habilidadExistente.getUsuarioId().equals(usuarioActual.getId())) {
            throw new RuntimeException("ACCESO DENEGADO: No tienes permiso para modificar esta habilidad");
        }

        habilidadExistente.setNombre(habilidadActualizada.getNombre());
        habilidadExistente.setPorcentaje(habilidadActualizada.getPorcentaje());

        return habilidadRepositoryPort.save(habilidadExistente);
    }

    public void eliminarHabilidad(Long id) {
        Usuario usuarioActual = obtenerUsuarioAutenticado();
        Habilidad habilidadExistente = habilidadRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Habilidad no encontrada"));

        // Seguridad IDOR
        if (!habilidadExistente.getUsuarioId().equals(usuarioActual.getId())) {
            throw new RuntimeException("ACCESO DENEGADO: No tienes permiso para eliminar esta habilidad");
        }

        habilidadRepositoryPort.deleteById(id);
    }
}