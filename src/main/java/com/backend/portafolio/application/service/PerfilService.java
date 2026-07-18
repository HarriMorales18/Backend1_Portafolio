package com.backend.portafolio.application.service;

import com.backend.portafolio.domain.model.Perfil;
import com.backend.portafolio.domain.model.Usuario;
import com.backend.portafolio.domain.port.out.PerfilRepositoryPort;
import com.backend.portafolio.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepositoryPort perfilRepositoryPort;
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    private Usuario obtenerUsuarioAutenticado() {
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepositoryPort.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Perfil guardarOActualizarPerfil(Perfil perfilData) {
        Usuario usuario = obtenerUsuarioAutenticado();
        Optional<Perfil> perfilExistente = perfilRepositoryPort.findByUsuarioId(usuario.getId());

        if (perfilExistente.isPresent()) {
            Perfil perfilActual = perfilExistente.get();
            perfilActual.setNombreCompleto(perfilData.getNombreCompleto());
            perfilActual.setTitular(perfilData.getTitular());
            perfilActual.setBiografia(perfilData.getBiografia());
            perfilActual.setUrlLinkedin(perfilData.getUrlLinkedin());
            perfilActual.setUrlGithub(perfilData.getUrlGithub());
            perfilActual.setUrlFotoPerfil(perfilData.getUrlFotoPerfil());
            return perfilRepositoryPort.save(perfilActual);
        } else {
            // Creamos uno nuevo
            perfilData.setUsuarioId(usuario.getId());
            return perfilRepositoryPort.save(perfilData);
        }
    }

    public Perfil obtenerMiPerfil() {
        Usuario usuario = obtenerUsuarioAutenticado();
        return perfilRepositoryPort.findByUsuarioId(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Aún no tienes un perfil configurado"));
    }
}