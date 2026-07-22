package com.backend.portafolio.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeRequest {
    private String nombreRemitente;
    private String correoRemitente;
    private String contenido;
}