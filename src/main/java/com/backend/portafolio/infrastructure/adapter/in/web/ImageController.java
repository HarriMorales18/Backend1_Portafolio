package com.backend.portafolio.infrastructure.adapter.in.web;

import com.backend.portafolio.application.port.in.UploadImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final UploadImageUseCase uploadImageUseCase;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        String imageUrl = uploadImageUseCase.uploadImage(file);

        return ResponseEntity.ok(Map.of("url", imageUrl));
    }
}