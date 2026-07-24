package com.backend.portafolio.infrastructure.adapter.in.web.dto;

import lombok.Data;

@Data
public class UpdatePerfilRequest {
    private String nombreCompleto;
    private String titular;
    private String biografia;
    private String urlLinkedin;
    private String urlGithub;
}