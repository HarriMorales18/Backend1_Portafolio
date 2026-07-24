package com.backend.portafolio.infrastructure.adapter.out.persistence.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;

    @Column(unique = true)
    private String correo;

    private String contrasena;
    private String foto;
    private String titularProfesional;
    private String biografia;
    private String telefono;
    private String ciudad;
    private String pais;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
