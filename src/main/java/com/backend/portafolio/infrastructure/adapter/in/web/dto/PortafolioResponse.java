package com.backend.portafolio.infrastructure.adapter.in.web.dto;

import com.backend.portafolio.domain.model.Experiencia;
import com.backend.portafolio.domain.model.Habilidad;
import com.backend.portafolio.domain.model.Perfil;
import com.backend.portafolio.domain.model.Proyecto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PortafolioResponse {
    private Perfil perfil;
    private List<Proyecto> proyectos;
    private List<Habilidad> habilidades;
    private List<Experiencia> experiencias;
}