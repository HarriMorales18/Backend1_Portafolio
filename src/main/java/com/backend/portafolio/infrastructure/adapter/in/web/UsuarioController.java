package com.backend.portafolio.infrastructure.adapter.in.web;

import com.backend.portafolio.application.service.UsuarioService;
import com.backend.portafolio.domain.model.Usuario;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.AuthResponse;
import com.backend.portafolio.infrastructure.adapter.in.web.dto.LoginRequest;
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

        AuthResponse response = AuthResponse.builder()
                .token(token)
                .build();

        return ResponseEntity.ok(response);
    }
}