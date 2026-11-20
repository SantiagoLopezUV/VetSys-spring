package com.vetsys.vetsys.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Entity
@Table(name = "cita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Cita
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("Identificador único")
    private Long id_cita;

    @NotNull(message = "La fecha es obligatoria")
    @Comment("Fecha de la cita")
    private LocalDate fecha;

    @NotBlank(message = "El motivo es obligatorio")
    @Comment("Motivo de la consulta")
    private String motivo;

    //fk de mascota
    // muchas citas pertenecen a una mascota
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_id", nullable = false)
    @Comment("Mascota atendida")
    private Mascota mascota;

    @NotBlank(message = "El nombre del veterinario es obligatorio")
    @Comment("Nombre del veterinario")
    private String veterinario;

}
