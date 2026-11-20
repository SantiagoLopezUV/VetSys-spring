package com.vetsys.vetsys.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "dueno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Dueno
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("Identificador único")
    private Long id_dueno;

    @NotBlank(message = "El nombre es obligatorio")
    @Comment("Nombre Completo")
    private String nombre;

    @NotBlank(message = "El telefono es obligatorio")
    @Comment("Teléfono de contacto")
    private String telefono;

    @NotBlank(message = "El correo es obligatorio")
    @Comment("Correo electrónico")
    private String correo;

    @NotBlank(message = "La dirección es obligatoria")
    @Comment("Dirección del domicilio")
    private String direccion;
}
