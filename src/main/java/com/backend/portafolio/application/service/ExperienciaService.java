package com.backend.portafolio.application.service;

import com.backend.portafolio.domain.model.Experiencia;
import com.backend.portafolio.domain.model.Usuario;
import com.backend.portafolio.domain.port.out.ExperienciaRepositoryPort;
import com.backend.portafolio.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienciaService {

    private final ExperienciaRepositoryPort experienciaRepositoryPort;
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    private Usuario obtenerUsuarioAutenticado() {
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepositoryPort.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Experiencia crearExperiencia(Experiencia experiencia) {
        Usuario usuario = obtenerUsuarioAutenticado();
        experiencia.setUsuarioId(usuario.getId());
        return experienciaRepositoryPort.save(experiencia);
    }

    public List<Experiencia> obtenerMisExperiencias() {
        Usuario usuario = obtenerUsuarioAutenticado();
        return experienciaRepositoryPort.findAllByUsuarioId(usuario.getId());
    }

    public Experiencia actualizarExperiencia(Long id, Experiencia experienciaActualizada) {
        Usuario usuarioActual = obtenerUsuarioAutenticado();
        Experiencia experienciaExistente = experienciaRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Experiencia no encontrada"));

        // Seguridad IDOR
        if (!experienciaExistente.getUsuarioId().equals(usuarioActual.getId())) {
            throw new RuntimeException("ACCESO DENEGADO: No tienes permiso para modificar esta experiencia");
        }

        experienciaExistente.setTitulo(experienciaActualizada.getTitulo());
        experienciaExistente.setInstitucion(experienciaActualizada.getInstitucion());
        experienciaExistente.setFechaInicio(experienciaActualizada.getFechaInicio());
        experienciaExistente.setFechaFin(experienciaActualizada.getFechaFin());
        experienciaExistente.setDescripcion(experienciaActualizada.getDescripcion());

        return experienciaRepositoryPort.save(experienciaExistente);
    }

    public void eliminarExperiencia(Long id) {
        Usuario usuarioActual = obtenerUsuarioAutenticado();
        Experiencia experienciaExistente = experienciaRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Experiencia no encontrada"));

        // Seguridad IDOR
        if (!experienciaExistente.getUsuarioId().equals(usuarioActual.getId())) {
            throw new RuntimeException("ACCESO DENEGADO: No tienes permiso para eliminar esta experiencia");
        }

        experienciaRepositoryPort.deleteById(id);
    }
}
