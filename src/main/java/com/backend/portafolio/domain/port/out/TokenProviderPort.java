package com.backend.portafolio.domain.port.out;

import com.backend.portafolio.domain.model.Usuario;

public interface TokenProviderPort {
    String generateToken(Usuario usuario);
}