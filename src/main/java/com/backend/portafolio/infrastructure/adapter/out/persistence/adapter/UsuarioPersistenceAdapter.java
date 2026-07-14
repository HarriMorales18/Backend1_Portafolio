package com.backend.portafolio.infrastructure.adapter.out.persistence.adapter;

import com.backend.portafolio.domain.model.Usuario;
import com.backend.portafolio.domain.port.out.UsuarioRepositoryPort;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import com.backend.portafolio.infrastructure.adapter.out.persistence.mapper.UsuarioMapper;
import com.backend.portafolio.infrastructure.adapter.out.persistence.repository.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository repository;
    private final UsuarioMapper mapper;

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        UsuarioEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Usuario> findByCorreo(String correo) {
        return repository.findByCorreo(correo)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return repository.existsByCorreo(correo);
    }
}