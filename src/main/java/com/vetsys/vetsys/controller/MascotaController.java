package com.vetsys.vetsys.controller;


import com.vetsys.vetsys.dto.Mascotas.MascotasDTOSalida;
import com.vetsys.vetsys.dto.Mascotas.MascotasDTOEntrada;
import com.vetsys.vetsys.entity.Dueno;
import com.vetsys.vetsys.entity.Mascota;
import com.vetsys.vetsys.repository.DuenoRepository;
import com.vetsys.vetsys.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private DuenoRepository duenoRepository;

    public MascotasDTOSalida dto(Mascota mascota)
    {
        Long id_dueno = mascota.getDueno() != null
                ? mascota.getDueno().getId_dueno() : null;
        String nombreDueno = mascota.getDueno() != null
                ? mascota.getDueno().getNombre() : null;
        return new MascotasDTOSalida(
                mascota.getId_mascota(),
                mascota.getNombre(),
                mascota.getEspecie(),
                id_dueno,
                nombreDueno
        );
    }

    @GetMapping
    public List<MascotasDTOSalida> getAll() {
        return mascotaRepository.findAll()
                .stream()
                .map(this::dto)
                .toList();
    }

    @GetMapping("/{id}")
    public MascotasDTOSalida getById(@PathVariable Long id) {
        return mascotaRepository.findById(id)
                .map(this::dto)
                .orElse(null);
    }

    //Registra una mascota asociando el dueño en el DTO de entrada
    // busca el Dueno por id, guarda en la BD y retorna el dto de salida
    @PostMapping
    public MascotasDTOSalida create(@RequestBody MascotasDTOEntrada entrada) {

        Dueno dueno = duenoRepository.findById(entrada.getIdDueno()).orElse(null);

        Mascota mascota = new Mascota();
        mascota.setNombre(entrada.getNombre());
        mascota.setEspecie(entrada.getEspecie());
        mascota.setRaza(entrada.getRaza());
        mascota.setEdad(entrada.getEdad());
        mascota.setDueno(dueno);

        Mascota guardada = mascotaRepository.save(mascota);
        return dto(guardada);
    }

    //actualiza mascotas con el id usadando el dto de entrada
    // pasa al dueño por id, y retorna el dto de salida
    @PutMapping("/{id}")
    public MascotasDTOSalida update(@PathVariable Long id, @RequestBody MascotasDTOEntrada entrada) {
        Dueno dueno = duenoRepository.findById(entrada.getIdDueno()).orElse(null);

        Mascota mascota = new Mascota();
        mascota.setId_mascota(id);
        mascota.setNombre(entrada.getNombre());
        mascota.setEspecie(entrada.getEspecie());
        mascota.setRaza(entrada.getRaza());
        mascota.setEdad(entrada.getEdad());
        mascota.setDueno(dueno);

        Mascota actualizada = mascotaRepository.save(mascota);
        return dto(actualizada);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mascotaRepository.deleteById(id);
    }
}
