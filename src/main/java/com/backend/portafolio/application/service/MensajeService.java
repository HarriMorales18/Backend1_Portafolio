package com.backend.portafolio.application.service;

import com.backend.portafolio.domain.model.Mensaje;
import com.backend.portafolio.domain.model.Usuario;
import com.backend.portafolio.domain.port.out.MensajeRepositoryPort;
import com.backend.portafolio.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MensajeService {

    private final MensajeRepositoryPort mensajeRepositoryPort;
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    // 1. MÉTODO PÚBLICO: Recibir un mensaje desde el frontend
    public void enviarMensaje(Long usuarioIdDestino, Mensaje mensaje) {
        // Validamos que el dueño del portafolio exista
        usuarioRepositoryPort.findById(usuarioIdDestino)
                .orElseThrow(() -> new RuntimeException("El usuario al que intentas contactar no existe"));

        mensaje.setUsuarioId(usuarioIdDestino);
        mensaje.setFechaEnvio(LocalDateTime.now()); // Registramos la hora exacta
        mensajeRepositoryPort.save(mensaje);
    }

    // 2. MÉTODO PRIVADO: Leer mi bandeja de entrada
    public List<Mensaje> obtenerMisMensajes() {
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuarioActual = usuarioRepositoryPort.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return mensajeRepositoryPort.findAllByUsuarioId(usuarioActual.getId());
    }
}