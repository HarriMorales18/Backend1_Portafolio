package com.backend.portafolio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proyecto {
    private Long id;
    private String titulo;
    private String descripcion;
    private String urlImagen;
    private String urlRepositorio;
    private String urlDemo;
    private Long usuarioId;
    private LocalDateTime fechaCreacion;
}