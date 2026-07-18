package com.backend.portafolio.infrastructure.adapter.out.persistence.repository;

import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilJpaRepository extends JpaRepository<PerfilEntity, Long> {
    Optional<PerfilEntity> findByUsuario_Id(Long usuarioId);
}