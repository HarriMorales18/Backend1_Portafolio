package com.backend.portafolio.infrastructure.adapter.out.persistence.adapter;

import com.backend.portafolio.domain.model.RefreshToken;
import com.backend.portafolio.domain.model.Usuario;
import com.backend.portafolio.domain.port.out.RefreshTokenPort;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.RefreshTokenEntity;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import com.backend.portafolio.infrastructure.adapter.out.persistence.repository.RefreshTokenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RefreshTokenAdapter implements RefreshTokenPort {

    private final RefreshTokenJpaRepository repository;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenEntity entity = new RefreshTokenEntity();
        // Mapeo manual simple para evitar problemas con librerías externas por ahora
        entity.setToken(refreshToken.getToken());
        entity.setExpiryDate(refreshToken.getExpiryDate());

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(refreshToken.getUsuario().getId());
        entity.setUsuario(usuarioEntity);

        RefreshTokenEntity savedEntity = repository.save(entity);
        refreshToken.setId(savedEntity.getId());

        return refreshToken;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return repository.findByToken(token).map(entity -> {
            Usuario usuario = new Usuario();
            usuario.setId(entity.getUsuario().getId());
            return new RefreshToken(
                    entity.getId(),
                    usuario,
                    entity.getToken(),
                    entity.getExpiryDate()
            );
        });
    }

    @Override
    public void deleteByUsuarioId(Long usuarioId) {
        repository.deleteByUsuarioId(usuarioId);
    }
}