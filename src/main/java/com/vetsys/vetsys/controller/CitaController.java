package com.vetsys.vetsys.controller;

import com.vetsys.vetsys.dto.Citas.CitasDTOEntrada;
import com.vetsys.vetsys.dto.Citas.CitasDTOSalida;
import com.vetsys.vetsys.entity.Cita;
import com.vetsys.vetsys.entity.Mascota;
import com.vetsys.vetsys.repository.CitaRepository;
import com.vetsys.vetsys.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController
{

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    public CitasDTOSalida dto(Cita cita)
    {
        Long idMascota = cita.getMascota() != null
                ? cita.getMascota().getId_mascota() : null;
        String nombreMascota = cita.getMascota() != null
                ? cita.getMascota().getNombre() : null;

        return new CitasDTOSalida(
                cita.getId_cita(),
                cita.getFecha(),
                cita.getMotivo(),
                idMascota,
                nombreMascota,
                cita.getVeterinario()
        );
    }

    @GetMapping
    public List<CitasDTOSalida> getAll()
    {
        return citaRepository.findAll()
                .stream()
                .map(this::dto)
                .toList();
    }

    @GetMapping("/{id}")
    public CitasDTOSalida getById(@PathVariable Long id)
    {
        return citaRepository.findById(id)
                .map(this::dto)
                .orElse(null);
    }

    @PostMapping
    public CitasDTOSalida create(@RequestBody CitasDTOEntrada entrada)
    {
        Mascota mascota = entrada.getIdMascota() != null
                ? mascotaRepository.findById(entrada.getIdMascota()).orElse(null)
                : null;

        Cita cita = new Cita();
        cita.setFecha(entrada.getFecha());
        cita.setMotivo(entrada.getMotivo());
        cita.setVeterinario(entrada.getVeterinario());
        cita.setMascota(mascota);

        Cita guardada = citaRepository.save(cita);
        return dto(guardada);
    }

    @PutMapping("/{id}")
    public CitasDTOSalida update(@PathVariable Long id, @RequestBody CitasDTOEntrada entrada)
    {
        Mascota mascota = entrada.getIdMascota() != null
                ? mascotaRepository.findById(entrada.getIdMascota()).orElse(null)
                : null;

        Cita cita = new Cita();
        cita.setId_cita(id);
        cita.setFecha(entrada.getFecha());
        cita.setMotivo(entrada.getMotivo());
        cita.setVeterinario(entrada.getVeterinario());
        cita.setMascota(mascota);

        Cita actualizada = citaRepository.save(cita);
        return dto(actualizada);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        citaRepository.deleteById(id);
    }
}
