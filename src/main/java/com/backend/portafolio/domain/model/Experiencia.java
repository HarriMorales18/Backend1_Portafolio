package com.backend.portafolio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Experiencia {
    private Long id;
    private String titulo;
    private String institucion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String descripcion;
    private Long usuarioId;
}