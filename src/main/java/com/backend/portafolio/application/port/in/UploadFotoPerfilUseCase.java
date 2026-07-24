package com.backend.portafolio.application.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFotoPerfilUseCase {
    String uploadFotoPerfil(Long perfilId, MultipartFile file);
}