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
public class Usuario {

    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String contrasena;
    private String foto;
    private String titularProfesional;
    private String biografia;
    private String telefono;
    private String ciudad;
    private String pais;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String githubUrl;
    private String linkedinUrl;
}