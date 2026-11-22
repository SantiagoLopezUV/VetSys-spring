package com.vetsys.vetsys.view;

import com.vetsys.vetsys.dto.Tratamientos.TratamientosDTOEntrada;
import com.vetsys.vetsys.entity.Cita;
import com.vetsys.vetsys.entity.Tratamiento;
import com.vetsys.vetsys.repository.CitaRepository;
import com.vetsys.vetsys.repository.TratamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TratamientoView {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private CitaRepository citaRepository;

    private void construirTratamiento(Tratamiento tratamiento, TratamientosDTOEntrada entrada)
    {
        Cita cita = entrada.getIdCita() != null
                ? citaRepository.findById(entrada.getIdCita()).orElse(null)
                : null;

        tratamiento.setDescripcion(entrada.getDescripcion());
        tratamiento.setDosis(entrada.getDosis());
        tratamiento.setCita(cita);
    }

    @GetMapping("/view/tratamientos")
    public String lista(Model model)
    {
        model.addAttribute("tratamientos", tratamientoRepository.findAll());
        return "tratamientos/list";
    }

    @GetMapping("/view/tratamientos/form")
    public String form(@RequestParam(value = "idCita", required = false) Long idCita, Model model)
    {
        TratamientosDTOEntrada entrada = new TratamientosDTOEntrada();
        if (idCita != null) {
            entrada.setIdCita(idCita);
        }

        model.addAttribute("tratamiento", entrada);
        model.addAttribute("citas", citaRepository.findAll());
        return "tratamientos/form";
    }

    @GetMapping("/view/tratamientos/edit/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Tratamiento tratamiento = tratamientoRepository.findById(id).orElse(null);
        if (tratamiento == null) {return "redirect:/view/tratamientos";}

        TratamientosDTOEntrada entrada = new TratamientosDTOEntrada();
        entrada.setId_tratamiento(tratamiento.getId_tratamiento());
        entrada.setDescripcion(tratamiento.getDescripcion());
        entrada.setDosis(tratamiento.getDosis());
        entrada.setIdCita(tratamiento.getCita() != null ? tratamiento.getCita().getId_cita() : null);

        model.addAttribute("tratamiento", entrada);
        model.addAttribute("citas", citaRepository.findAll());
        return "tratamientos/form";
    }

    @PostMapping("/view/tratamientos/save")
    public String save(@ModelAttribute("tratamiento") TratamientosDTOEntrada entrada, RedirectAttributes rs)
    {
        boolean edicion = entrada.getId_tratamiento() != null;

        Tratamiento tratamiento = new Tratamiento();
        if (edicion) {tratamiento.setId_tratamiento(entrada.getId_tratamiento());}
        construirTratamiento(tratamiento, entrada);

        tratamientoRepository.save(tratamiento);
        String mensaje = edicion
                ? "Tratamiento actualizado correctamente"
                : "Tratamiento registrado correctamente";

        rs.addFlashAttribute("message", mensaje);
        return "redirect:/view/tratamientos";
    }

    @PostMapping("/view/tratamientos/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rs)
    {
        tratamientoRepository.deleteById(id);
        rs.addFlashAttribute("message", "Tratamiento eliminado correctamente");
        return "redirect:/view/tratamientos";
    }
}
