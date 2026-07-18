package com.backend.portafolio.domain.port.out;

import com.backend.portafolio.domain.model.Experiencia;
import java.util.List;
import java.util.Optional;

public interface ExperienciaRepositoryPort {
    Experiencia save(Experiencia experiencia);
    Optional<Experiencia> findById(Long id);
    List<Experiencia> findAllByUsuarioId(Long usuarioId);
    void deleteById(Long id);
}
