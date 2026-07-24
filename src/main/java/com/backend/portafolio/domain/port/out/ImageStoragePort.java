package com.backend.portafolio.domain.port.out;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface ImageStoragePort {
    String uploadImage(MultipartFile file) throws IOException;
}