package com.vetsys.vetsys.controller;

import com.vetsys.vetsys.dto.Tratamientos.TratamientosDTOEntrada;
import com.vetsys.vetsys.dto.Tratamientos.TratamientosDTOSalida;
import com.vetsys.vetsys.entity.Cita;
import com.vetsys.vetsys.entity.Tratamiento;
import com.vetsys.vetsys.repository.CitaRepository;
import com.vetsys.vetsys.repository.TratamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
public class TratamientoController {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private CitaRepository citaRepository;

    public TratamientosDTOSalida dto(Tratamiento tratamiento) {
        Long idCita = tratamiento.getCita() != null
                ? tratamiento.getCita().getId_cita() : null;
        String motivoCita = tratamiento.getCita() != null
                ? tratamiento.getCita().getMotivo() : null;

        return new TratamientosDTOSalida(
                tratamiento.getId_tratamiento(),
                tratamiento.getDescripcion(),
                tratamiento.getDosis(),
                idCita,
                motivoCita
        );
    }

    @GetMapping
    public List<TratamientosDTOSalida> getAll() {
        return tratamientoRepository.findAll()
                .stream()
                .map(this::dto)
                .toList();
    }

    @GetMapping("/{id}")
    public TratamientosDTOSalida getById(@PathVariable Long id) {
        return tratamientoRepository.findById(id)
                .map(this::dto)
                .orElse(null);
    }

    @PostMapping
    public TratamientosDTOSalida create(@RequestBody TratamientosDTOEntrada entrada) {
        Cita cita = entrada.getIdCita() != null
                ? citaRepository.findById(entrada.getIdCita()).orElse(null)
                : null;

        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setDescripcion(entrada.getDescripcion());
        tratamiento.setDosis(entrada.getDosis());
        tratamiento.setCita(cita);

        Tratamiento guardado = tratamientoRepository.save(tratamiento);
        return dto(guardado);
    }

    @PutMapping("/{id}")
    public TratamientosDTOSalida update(@PathVariable Long id, @RequestBody TratamientosDTOEntrada entrada) {
        Cita cita = entrada.getIdCita() != null
                ? citaRepository.findById(entrada.getIdCita()).orElse(null)
                : null;

        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setId_tratamiento(id);
        tratamiento.setDescripcion(entrada.getDescripcion());
        tratamiento.setDosis(entrada.getDosis());
        tratamiento.setCita(cita);

        Tratamiento actualizado = tratamientoRepository.save(tratamiento);
        return dto(actualizado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tratamientoRepository.deleteById(id);
    }
}
