package com.backend.portafolio.infrastructure.adapter.out.persistence;

import com.backend.portafolio.domain.model.Perfil;
import com.backend.portafolio.domain.port.out.PerfilRepositoryPort;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.PerfilEntity;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import com.backend.portafolio.infrastructure.adapter.out.persistence.repository.PerfilJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PerfilRepositoryAdapter implements PerfilRepositoryPort {

    private final PerfilJpaRepository perfilJpaRepository;

    @Override
    public Perfil save(Perfil perfil) {
        PerfilEntity entity = toEntity(perfil);
        return toDomain(perfilJpaRepository.save(entity));
    }

    @Override
    public Optional<Perfil> findByUsuarioId(Long usuarioId) {
        return perfilJpaRepository.findByUsuario_Id(usuarioId).map(this::toDomain);
    }

    private PerfilEntity toEntity(Perfil domain) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(domain.getUsuarioId());

        return PerfilEntity.builder()
                .id(domain.getId())
                .urlFotoPerfil(domain.getUrlFotoPerfil())
                .nombreCompleto(domain.getNombreCompleto())
                .titular(domain.getTitular())
                .biografia(domain.getBiografia())
                .urlLinkedin(domain.getUrlLinkedin())
                .urlGithub(domain.getUrlGithub())
                .usuario(usuario)
                .build();
    }

    private Perfil toDomain(PerfilEntity entity) {
        return Perfil.builder()
                .id(entity.getId())
                .urlFotoPerfil(entity.getUrlFotoPerfil())
                .nombreCompleto(entity.getNombreCompleto())
                .titular(entity.getTitular())
                .biografia(entity.getBiografia())
                .urlLinkedin(entity.getUrlLinkedin())
                .urlGithub(entity.getUrlGithub())
                .usuarioId(entity.getUsuario().getId())
                .build();
    }
}
