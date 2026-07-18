package com.backend.portafolio.infrastructure.adapter.out.persistence;

import com.backend.portafolio.domain.model.Experiencia;
import com.backend.portafolio.domain.port.out.ExperienciaRepositoryPort;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.ExperienciaEntity;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import com.backend.portafolio.infrastructure.adapter.out.persistence.repository.ExperienciaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExperienciaRepositoryAdapter implements ExperienciaRepositoryPort {

    private final ExperienciaJpaRepository experienciaJpaRepository;

    @Override
    public Experiencia save(Experiencia experiencia) {
        ExperienciaEntity entity = toEntity(experiencia);
        return toDomain(experienciaJpaRepository.save(entity));
    }

    @Override
    public Optional<Experiencia> findById(Long id) {
        return experienciaJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Experiencia> findAllByUsuarioId(Long usuarioId) {
        return experienciaJpaRepository.findAllByUsuario_Id(usuarioId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        experienciaJpaRepository.deleteById(id);
    }

    private ExperienciaEntity toEntity(Experiencia domain) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(domain.getUsuarioId());

        return ExperienciaEntity.builder()
                .id(domain.getId())
                .titulo(domain.getTitulo())
                .institucion(domain.getInstitucion())
                .fechaInicio(domain.getFechaInicio())
                .fechaFin(domain.getFechaFin())
                .descripcion(domain.getDescripcion())
                .usuario(usuario)
                .build();
    }

    private Experiencia toDomain(ExperienciaEntity entity) {
        return Experiencia.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .institucion(entity.getInstitucion())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .descripcion(entity.getDescripcion())
                .usuarioId(entity.getUsuario().getId())
                .build();
    }
}
