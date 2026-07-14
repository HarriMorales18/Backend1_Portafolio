package com.backend.portafolio.infrastructure.adapter.in.web.dto;

import lombok.Data;

@Data
public class RegistroUsuarioRequest {
    private String nombres;
    private String apellidos;
    private String correo;
    private String contrasena;
}