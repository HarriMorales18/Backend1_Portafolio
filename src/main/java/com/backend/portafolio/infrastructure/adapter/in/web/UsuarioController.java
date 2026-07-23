package com.backend.portafolio.infrastructure.adapter.in.web;

import com.backend.portafolio.application.service.RefreshTokenService;
import com.backend.portafolio.application.service.UsuarioService;
import com.backend.portafolio.domain.model.RefreshToken;
import com.backend.portafolio.domain.model.Usuario;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.AuthResponse;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.LoginRequest;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.RefreshTokenRequestDto;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.RegistroUsuarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody RegistroUsuarioRequest request) {

        Usuario nuevoUsuario = Usuario.builder()
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .correo(request.getCorreo())
                .contrasena(request.getContrasena())
                .build();

        Usuario usuarioGuardado = usuarioService.registrarUsuario(nuevoUsuario);

        return new ResponseEntity<>(usuarioGuardado, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = usuarioService.login(request.getCorreo(), request.getContrasena());

        Usuario usuario = usuarioService.buscarPorCorreo(request.getCorreo());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(usuario.getId());

        AuthResponse response = AuthResponse.builder()
                .token(token)
                .refreshToken(refreshToken.getToken())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequestDto request) {
        return refreshTokenService.findByToken(request.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsuario)
                .map(usuario -> {

                    String nuevoAccessToken = usuarioService.generarToken(usuario);

                    return ResponseEntity.ok(AuthResponse.builder()
                            .token(nuevoAccessToken)
                            .refreshToken(request.getRefreshToken())
                            .build());
                })
                .orElseThrow(() -> new RuntimeException("Refresh token no encontrado o inválido"));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequestDto request) {
        refreshTokenService.findByToken(request.getRefreshToken())
                .ifPresent(token -> {
                    refreshTokenService.deleteByUsuarioId(token.getUsuario().getId());
                });

        return ResponseEntity.ok("Sesión cerrada exitosamente");
    }
}