package com.backend.portafolio.domain.port.out;

import com.backend.portafolio.domain.model.Usuario;
import java.util.Optional;

public interface UsuarioRepositoryPort {

    Usuario save(Usuario usuario);
    Optional<Usuario> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}