package com.backend.portafolio.application.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageUseCase {
    String uploadImage(MultipartFile file);
}