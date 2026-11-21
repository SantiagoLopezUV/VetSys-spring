package com.vetsys.vetsys.dto.Tratamientos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TratamientosDTOSalida {
    private Long idTratamiento;
    private String descripcion;
    private String dosis;
    private Long idCita;
    private String motivoCita;
}
