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
public class Mensaje {
    private Long id;
    private String nombreRemitente;
    private String correoRemitente;
    private String contenido;
    private LocalDateTime fechaEnvio;
    private Long usuarioId;
}