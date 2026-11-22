package com.vetsys.vetsys.dto.Citas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CitasDTOEntrada {
    private Long id_cita;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private String motivo;
    private Long idMascota;
    private String veterinario;
}
