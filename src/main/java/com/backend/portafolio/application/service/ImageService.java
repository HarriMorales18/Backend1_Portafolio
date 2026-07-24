package com.backend.portafolio.application.service;

import com.backend.portafolio.application.port.in.UploadImageUseCase;
import com.backend.portafolio.domain.port.out.ImageStoragePort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService implements UploadImageUseCase {

    private final ImageStoragePort imageStoragePort;

    public ImageService(ImageStoragePort imageStoragePort) {
        this.imageStoragePort = imageStoragePort;
    }

    @Override
    public String uploadImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("El archivo de imagen no puede estar vacío");
        }

        // 2. Aquí podrías agregar más validaciones (tamaño, tipo de archivo, etc.)
        // if (!file.getContentType().startsWith("image/")) { ... }

        try {
            return imageStoragePort.uploadImage(file);
        } catch (java.io.IOException e) {
            throw new RuntimeException("Error inesperado al intentar subir la imagen", e);
        }
    }
}