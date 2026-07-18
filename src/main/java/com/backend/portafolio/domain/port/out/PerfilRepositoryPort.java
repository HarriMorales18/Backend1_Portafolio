package com.backend.portafolio.domain.port.out;

import com.backend.portafolio.domain.model.Perfil;
import java.util.Optional;

public interface PerfilRepositoryPort {
    Perfil save(Perfil perfil);
    Optional<Perfil> findByUsuarioId(Long usuarioId);
}
