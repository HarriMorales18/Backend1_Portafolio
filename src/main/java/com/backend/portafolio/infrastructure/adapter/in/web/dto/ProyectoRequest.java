package com.backend.portafolio.infrastructure.adapter.in.web.dto;

import lombok.Data;

@Data
public class ProyectoRequest {
    private String titulo;
    private String descripcion;
    private String urlImagen;
    private String urlRepositorio;
    private String urlDemo;
}