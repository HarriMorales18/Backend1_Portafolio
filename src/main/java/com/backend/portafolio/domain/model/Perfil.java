package com.backend.portafolio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Perfil {
    private Long id;
    private String urlFotoPerfil;
    private String nombreCompleto;
    private String titular;
    private String biografia;
    private String urlLinkedin;
    private String urlGithub;
    private Long usuarioId;
}