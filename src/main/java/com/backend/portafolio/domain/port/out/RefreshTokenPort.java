package com.backend.portafolio.domain.port.out;

import com.backend.portafolio.domain.model.RefreshToken;
import java.util.Optional;

public interface RefreshTokenPort {
    RefreshToken save(RefreshToken refreshToken);
    Optional<RefreshToken> findByToken(String token);
    void deleteByUsuarioId(Long usuarioId);
}