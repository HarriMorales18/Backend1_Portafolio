package com.backend.portafolio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Habilidad {
    private Long id;
    private String nombre;
    private Integer porcentaje;
    private Long usuarioId;
}