package com.backend.portafolio.infrastructure.adapter.in.web.dto;

import lombok.Data;

@Data
public class HabilidadRequest {
    private String nombre;
    private Integer porcentaje;
}