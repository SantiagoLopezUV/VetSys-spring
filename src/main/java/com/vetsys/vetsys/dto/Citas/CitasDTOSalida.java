package com.vetsys.vetsys.dto.Citas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CitasDTOSalida {
    private Long idCita;
    private LocalDate fecha;
    private String motivo;
    private Long idMascota;
    private String nombreMascota;
    private String veterinario;
}
