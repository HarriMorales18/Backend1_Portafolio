package com.backend.portafolio.infrastructure.adapter.in.web.dto;

import lombok.Data;

@Data
public class PerfilResponse {
    private Long id;
    private String nombreCompleto;
    private String titular;
    private String biografia;
    private String urlFotoPerfil;
    private String urlLinkedin;
    private String urlGithub;
    private Long usuarioId;
}