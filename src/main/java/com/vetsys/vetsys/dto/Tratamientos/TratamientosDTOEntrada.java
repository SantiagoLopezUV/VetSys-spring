package com.vetsys.vetsys.dto.Tratamientos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TratamientosDTOEntrada {
    private Long id_tratamiento;
    private String descripcion;
    private String dosis;
    private Long idCita;
}
