package com.backend.portafolio.domain.port.out;

import com.backend.portafolio.domain.model.Proyecto;
import java.util.List;
import java.util.Optional;

public interface ProyectoRepositoryPort {
    Proyecto save(Proyecto proyecto);

    Optional<Proyecto> findById(Long id);

    List<Proyecto> findAllByUsuarioId(Long usuarioId);

    void deleteById(Long id);
}