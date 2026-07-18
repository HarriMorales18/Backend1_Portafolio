package com.backend.portafolio.infrastructure.adapter.out.persistence.repository;

import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.HabilidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabilidadJpaRepository extends JpaRepository<HabilidadEntity, Long> {
    List<HabilidadEntity> findAllByUsuario_Id(Long usuarioId);
}