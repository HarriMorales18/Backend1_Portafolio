package com.backend.portafolio.infrastructure.adapter.out.persistence;

import com.backend.portafolio.domain.model.Proyecto;
import com.backend.portafolio.domain.port.out.ProyectoRepositoryPort;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.ProyectoEntity;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import com.backend.portafolio.infrastructure.adapter.out.persistence.repository.ProyectoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProyectoRepositoryAdapter implements ProyectoRepositoryPort {

    private final ProyectoJpaRepository proyectoJpaRepository;

    @Override
    public Proyecto save(Proyecto proyecto) {
        ProyectoEntity entity = toEntity(proyecto);
        ProyectoEntity savedEntity = proyectoJpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Proyecto> findById(Long id) {
        return proyectoJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Proyecto> findAllByUsuarioId(Long usuarioId) {
        return proyectoJpaRepository.findAllByUsuarioId(usuarioId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        proyectoJpaRepository.deleteById(id);
    }


    private ProyectoEntity toEntity(Proyecto domain) {
        // Creamos la referencia al usuario para mantener la integridad referencial (Clave Foránea)
        UsuarioEntity usuarioReferencia = new UsuarioEntity();
        usuarioReferencia.setId(domain.getUsuarioId());

        return ProyectoEntity.builder()
                .id(domain.getId())
                .titulo(domain.getTitulo())
                .descripcion(domain.getDescripcion())
                .urlImagen(domain.getUrlImagen())
                .urlRepositorio(domain.getUrlRepositorio())
                .urlDemo(domain.getUrlDemo())
                .usuario(usuarioReferencia) // Pasamos la entidad en lugar del ID suelto
                .fechaCreacion(domain.getFechaCreacion())
                .build();
    }

    private Proyecto toDomain(ProyectoEntity entity) {
        return Proyecto.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .descripcion(entity.getDescripcion())
                .urlImagen(entity.getUrlImagen())
                .urlRepositorio(entity.getUrlRepositorio())
                .urlDemo(entity.getUrlDemo())
                .usuarioId(entity.getUsuario().getId()) // Extraemos el ID de la entidad
                .fechaCreacion(entity.getFechaCreacion())
                .build();
    }
}