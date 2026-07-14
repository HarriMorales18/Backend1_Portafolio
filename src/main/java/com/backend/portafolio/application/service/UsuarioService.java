package com.backend.portafolio.application.service;

import com.backend.portafolio.domain.model.Usuario;
import com.backend.portafolio.domain.port.out.PasswordEncoderPort;
import com.backend.portafolio.domain.port.out.TokenProviderPort;
import com.backend.portafolio.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenProviderPort tokenProviderPort;

    public Usuario registrarUsuario(Usuario nuevoUsuario) {

        if (usuarioRepositoryPort.existsByCorreo(nuevoUsuario.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado en la plataforma");
        }

        nuevoUsuario.setFechaCreacion(LocalDateTime.now());
        nuevoUsuario.setFechaActualizacion(LocalDateTime.now());

        String contrasenaEncriptada = passwordEncoderPort.encode(nuevoUsuario.getContrasena());
        nuevoUsuario.setContrasena(contrasenaEncriptada);

        return usuarioRepositoryPort.save(nuevoUsuario);
    }

    public String login(String correo, String contrasenaPlana) {

        Usuario usuario = usuarioRepositoryPort.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoderPort.matches(contrasenaPlana, usuario.getContrasena())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return tokenProviderPort.generateToken(usuario);
    }
}