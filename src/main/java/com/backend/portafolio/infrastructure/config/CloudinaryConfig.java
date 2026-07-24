package com.backend.portafolio.infrastructure.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    // Si no encuentra la variable en el .yml, usará "wuenqpan"
    @Value("${cloudinary.cloud-name:wuenqpan}")
    private String cloudName;

    // Si no lo encuentra, usará tu API Key
    @Value("${cloudinary.api-key:323679837997148}")
    private String apiKey;

    // Si no lo encuentra, usará tu API Secret (asegúrate de poner el completo aquí)
    @Value("${cloudinary.api-secret:tPRBzE7HggsTRR6b2fxvl9-ASmo}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }
}