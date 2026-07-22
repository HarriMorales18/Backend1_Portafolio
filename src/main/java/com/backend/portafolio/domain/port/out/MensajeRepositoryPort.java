package com.backend.portafolio.domain.port.out;

import com.backend.portafolio.domain.model.Mensaje;
import java.util.List;

public interface MensajeRepositoryPort {
    Mensaje save(Mensaje mensaje);
    List<Mensaje> findAllByUsuarioId(Long usuarioId);
}