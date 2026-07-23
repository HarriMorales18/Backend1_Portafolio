package com.backend.portafolio.domain.model;

import java.time.Instant;

public class RefreshToken {
    private Long id;
    private Usuario usuario; // Tu modelo de dominio Usuario (NO la entidad JPA)
    private String token;
    private Instant expiryDate;

    public RefreshToken() {
    }

    public RefreshToken(Long id, Usuario usuario, String token, Instant expiryDate) {
        this.id = id;
        this.usuario = usuario;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Instant getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Instant expiryDate) { this.expiryDate = expiryDate; }
}