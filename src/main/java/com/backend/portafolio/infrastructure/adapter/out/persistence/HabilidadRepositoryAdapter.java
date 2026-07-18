package com.backend.portafolio.infrastructure.adapter.out.persistence;

import com.backend.portafolio.domain.model.Habilidad;
import com.backend.portafolio.domain.port.out.HabilidadRepositoryPort;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.HabilidadEntity;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import com.backend.portafolio.infrastructure.adapter.out.persistence.repository.HabilidadJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HabilidadRepositoryAdapter implements HabilidadRepositoryPort {

    private final HabilidadJpaRepository habilidadJpaRepository;

    @Override
    public Habilidad save(Habilidad habilidad) {
        HabilidadEntity entity = toEntity(habilidad);
        return toDomain(habilidadJpaRepository.save(entity));
    }

    @Override
    public Optional<Habilidad> findById(Long id) {
        return habilidadJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Habilidad> findAllByUsuarioId(Long usuarioId) {
        return habilidadJpaRepository.findAllByUsuario_Id(usuarioId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        habilidadJpaRepository.deleteById(id);
    }

    private HabilidadEntity toEntity(Habilidad domain) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(domain.getUsuarioId());

        return HabilidadEntity.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .porcentaje(domain.getPorcentaje())
                .usuario(usuario)
                .build();
    }

    private Habilidad toDomain(HabilidadEntity entity) {
        return Habilidad.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .porcentaje(entity.getPorcentaje())
                .usuarioId(entity.getUsuario().getId())
                .build();
    }
}