package com.vetsys.vetsys.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "mascota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Mascota
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("Identificador único")
    private Long id_mascota;

    @NotBlank(message = "El nombre es obligatorio")
    @Comment("Nombre de la mascota")
    private String nombre;

    @NotBlank(message = "La especie es obligatoria")
    @Comment("Perro, gato, conejo, etc.")
    private String especie;

    @NotBlank(message = "La raza obligatoria")
    @Comment("Raza o tipo")
    private String raza;

    @NotNull(message = "La edad es obligatoria")
    @Comment("Edad de la mascota")
    private Integer edad;

    //fk dueño
    // @ManyToOne crea la relación muchos a uno, porque la fk se guarda en la tabla mascota
    //JoinColum crea la columna como fk en la tabla
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "dueno_id", nullable = false)
    @Comment("Relación con el dueño")
    private Dueno dueno;
}
