package com.backend.portafolio.infrastructure.adapter.in.web.dto;

import lombok.Data;

@Data
public class RefreshTokenRequestDto {
    private String refreshToken;
}