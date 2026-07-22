package com.backend.portafolio.infrastructure.adapter.out.persistence;

import com.backend.portafolio.domain.model.Mensaje;
import com.backend.portafolio.domain.port.out.MensajeRepositoryPort;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.MensajeEntity;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import com.backend.portafolio.infrastructure.adapter.out.persistence.repository.MensajeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MensajeRepositoryAdapter implements MensajeRepositoryPort {

    private final MensajeJpaRepository mensajeJpaRepository;

    @Override
    public Mensaje save(Mensaje mensaje) {
        MensajeEntity entity = toEntity(mensaje);
        return toDomain(mensajeJpaRepository.save(entity));
    }

    @Override
    public List<Mensaje> findAllByUsuarioId(Long usuarioId) {
        return mensajeJpaRepository.findAllByUsuario_IdOrderByFechaEnvioDesc(usuarioId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private MensajeEntity toEntity(Mensaje domain) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(domain.getUsuarioId());

        return MensajeEntity.builder()
                .id(domain.getId())
                .nombreRemitente(domain.getNombreRemitente())
                .correoRemitente(domain.getCorreoRemitente())
                .contenido(domain.getContenido())
                .fechaEnvio(domain.getFechaEnvio())
                .usuario(usuario)
                .build();
    }

    private Mensaje toDomain(MensajeEntity entity) {
        return Mensaje.builder()
                .id(entity.getId())
                .nombreRemitente(entity.getNombreRemitente())
                .correoRemitente(entity.getCorreoRemitente())
                .contenido(entity.getContenido())
                .fechaEnvio(entity.getFechaEnvio())
                .usuarioId(entity.getUsuario().getId())
                .build();
    }
}