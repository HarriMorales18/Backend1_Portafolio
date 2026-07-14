CREATE TABLE usuarios (
                          id BIGSERIAL PRIMARY KEY, -- ¡Aquí está el cambio!
                          nombres VARCHAR(100) NOT NULL,
                          apellidos VARCHAR(100) NOT NULL,
                          correo VARCHAR(150) NOT NULL UNIQUE,
                          contrasena VARCHAR(255) NOT NULL,
                          foto VARCHAR(255),
                          titular_profesional VARCHAR(150),
                          biografia TEXT,
                          telefono VARCHAR(20),
                          ciudad VARCHAR(100),
                          pais VARCHAR(100),
                          fecha_creacion TIMESTAMP NOT NULL,
                          fecha_actualizacion TIMESTAMP NOT NULL
);