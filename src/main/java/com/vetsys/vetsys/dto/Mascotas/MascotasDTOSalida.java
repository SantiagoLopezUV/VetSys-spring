package com.vetsys.vetsys.dto.Mascotas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// DTO de salida para listar mascotas
public class MascotasDTOSalida
{
    private long idMascota;
    private String nombre;
    private String especie;
    private long idDueno;
    private String nombreDueno;
}
