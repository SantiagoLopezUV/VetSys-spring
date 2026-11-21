package com.vetsys.vetsys.controller;

import com.vetsys.vetsys.dto.DuenoDTO;
import com.vetsys.vetsys.entity.Dueno;
import com.vetsys.vetsys.repository.DuenoRepository;
import jakarta.validation.constraints.Past;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duenos")

public class DuenoController
{
    @Autowired
    private DuenoRepository duenoRepository;

    //pasar los datos con el DTO
    public DuenoDTO dto(Dueno dueno)
    {
        return new DuenoDTO(
                dueno.getId_dueno(),
                dueno.getNombre()
        );
    }

    @GetMapping
    public List<DuenoDTO> getAll()
    {
        return duenoRepository.findAll()
                .stream()
                .map(this::dto)
                .toList();
    }

    @GetMapping("/{id}")
    public DuenoDTO getById(@PathVariable Long id)
    {
        return duenoRepository
                .findById(id)
                .map(this::dto)
                .orElse(null);
    }

    @PostMapping("/{id}")
    public DuenoDTO create(@RequestBody Dueno duenos)
    {
        Dueno dueno = duenoRepository.save(duenos);
        return dto(dueno);
    }

    @PutMapping("/{id}")
    public DuenoDTO update(@PathVariable Long id, @RequestBody Dueno duenos)
    {
        duenos.setId_dueno(id);
        return dto(duenos);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        duenoRepository.deleteById(id);
    }
}
