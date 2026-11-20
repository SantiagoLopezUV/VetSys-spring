package com.vetsys.vetsys.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Comment;


@Entity
@Table(name = "tratamiento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("Identificador único")
    private Long id_tratamiento;

    @NotBlank(message = "La descripción es obligatoria")
    @Comment("Medicamento o procedimiento")
    private String descripcion;

    @NotBlank(message = "La dosis es obligatoria")
    @Comment("Detalle de dosis o frecuencia")
    private String dosis;

    //fk cita
    // muchos tratamientos pertenecen a una cita
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cita_id" , nullable = false)
    @Comment("Cita en la que se aplicó")
    private Cita cita;


}
