package com.backend.portafolio.application.service;

import com.backend.portafolio.domain.model.RefreshToken;
import com.backend.portafolio.domain.model.Usuario;
import com.backend.portafolio.domain.port.out.RefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenPort refreshTokenPort;

    private final long REFRESH_TOKEN_EXPIRATION_MS = 604800000L;

    @Transactional
    public RefreshToken createRefreshToken(Long usuarioId) {

        refreshTokenPort.deleteByUsuarioId(usuarioId);

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsuario(usuario);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRATION_MS));

        return refreshTokenPort.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenPort.findByToken(token);
    }

    @Transactional
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenPort.deleteByUsuarioId(token.getUsuario().getId());
            throw new RuntimeException("El Refresh Token ha expirado. Por favor, inicie sesión nuevamente.");
        }
        return token;
    }

    @Transactional
    public void deleteByUsuarioId(Long usuarioId) {
        refreshTokenPort.deleteByUsuarioId(usuarioId);
    }
}