package com.backend.portafolio.infrastructure.adapter.in.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
}