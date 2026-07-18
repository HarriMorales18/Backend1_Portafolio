package com.backend.portafolio.infrastructure.adapter.in.web.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ExperienciaRequest {
    private String titulo;
    private String institucion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String descripcion;
}