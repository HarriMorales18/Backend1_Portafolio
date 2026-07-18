package com.backend.portafolio.domain.port.out;

import com.backend.portafolio.domain.model.Habilidad;
import java.util.List;
import java.util.Optional;

public interface HabilidadRepositoryPort {
    Habilidad save(Habilidad habilidad);
    Optional<Habilidad> findById(Long id);
    List<Habilidad> findAllByUsuarioId(Long usuarioId);
    void deleteById(Long id);
}