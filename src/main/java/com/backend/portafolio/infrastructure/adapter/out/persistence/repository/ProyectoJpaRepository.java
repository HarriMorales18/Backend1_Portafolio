package com.backend.portafolio.infrastructure.adapter.out.persistence.repository;

import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.ProyectoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoJpaRepository extends JpaRepository<ProyectoEntity, Long> {

    List<ProyectoEntity> findAllByUsuarioId(Long usuarioId);
}