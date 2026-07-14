package com.backend.portafolio.infrastructure.adapter.out.persistence.mapper;

import com.backend.portafolio.domain.model.Usuario;
import com.backend.portafolio.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    UsuarioEntity toEntity(Usuario usuario);

    Usuario toDomain(UsuarioEntity usuarioEntity);
}