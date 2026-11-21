package com.vetsys.vetsys.dto.Mascotas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//DTO de entrada para crear o editar las mascotas
public class MascotasDTOEntrada
{
    private Long id_mascota;
    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private Long idDueno;
}
