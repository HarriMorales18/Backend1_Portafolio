package com.backend.portafolio.application.port.in;

import com.backend.portafolio.domain.model.Perfil;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.UpdatePerfilRequest;

public interface UpdatePerfilUseCase {
    Perfil updatePerfil(Long perfilId, UpdatePerfilRequest request);
}