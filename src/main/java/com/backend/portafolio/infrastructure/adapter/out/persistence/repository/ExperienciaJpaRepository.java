package com.backend.portafolio.infrastructure.adapter.out.persistence.repository;

import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.ExperienciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienciaJpaRepository extends JpaRepository<ExperienciaEntity, Long> {
    List<ExperienciaEntity> findAllByUsuario_Id(Long usuarioId);
}
