package com.backend.portafolio.infrastructure.adapter.out.persistence.repository;

import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeJpaRepository extends JpaRepository<MensajeEntity, Long> {
    List<MensajeEntity> findAllByUsuario_IdOrderByFechaEnvioDesc(Long usuarioId);
}