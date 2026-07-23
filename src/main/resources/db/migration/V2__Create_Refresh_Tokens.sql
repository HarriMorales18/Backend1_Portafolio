CREATE TABLE refresh_tokens (
                                id SERIAL PRIMARY KEY,
                                usuario_id BIGINT NOT NULL,
                                token VARCHAR(255) NOT NULL UNIQUE,
                                expiry_date TIMESTAMP NOT NULL,
                                CONSTRAINT fk_usuario_token FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);