package com.vetsys.vetsys.view;

import com.vetsys.vetsys.dto.Citas.CitasDTOEntrada;
import com.vetsys.vetsys.entity.Cita;
import com.vetsys.vetsys.entity.Mascota;
import com.vetsys.vetsys.repository.CitaRepository;
import com.vetsys.vetsys.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CitaView {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    private void construirCita(Cita cita, CitasDTOEntrada entrada) {
        Mascota mascota = entrada.getIdMascota() != null
                ? mascotaRepository.findById(entrada.getIdMascota()).orElse(null)
                : null;

        cita.setFecha(entrada.getFecha());
        cita.setMotivo(entrada.getMotivo());
        cita.setVeterinario(entrada.getVeterinario());
        cita.setMascota(mascota);
    }

    @GetMapping("/view/citas")
    public String lista(Model model) {
        model.addAttribute("citas", citaRepository.findAll());
        return "citas/list";
    }

    @GetMapping("/view/citas/form")
    public String form(Model model) {
        model.addAttribute("cita", new CitasDTOEntrada());
        model.addAttribute("mascotas", mascotaRepository.findAll());
        return "citas/form";
    }

    @GetMapping("/view/citas/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Cita cita = citaRepository.findById(id).orElse(null);
        if (cita == null) {
            return "redirect:/view/citas";
        }

        CitasDTOEntrada entrada = new CitasDTOEntrada();
        entrada.setId_cita(cita.getId_cita());
        entrada.setFecha(cita.getFecha());
        entrada.setMotivo(cita.getMotivo());
        entrada.setVeterinario(cita.getVeterinario());
        entrada.setIdMascota(cita.getMascota() != null ? cita.getMascota().getId_mascota() : null);

        model.addAttribute("cita", entrada);
        model.addAttribute("mascotas", mascotaRepository.findAll());
        return "citas/form";
    }

    @PostMapping("/view/citas/save")
    public String save(@ModelAttribute("cita") CitasDTOEntrada entrada, RedirectAttributes rs) {
        boolean edicion = entrada.getId_cita() != null;

        Cita cita = new Cita();
        if (edicion) {
            cita.setId_cita(entrada.getId_cita());
        }
        construirCita(cita, entrada);

        citaRepository.save(cita);
        String mensaje = edicion
                ? "Cita actualizada correctamente"
                : "Cita registrada correctamente";

        rs.addFlashAttribute("message", mensaje);
        return "redirect:/view/citas";
    }

    @PostMapping("/view/citas/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rs) {
        try {
            citaRepository.deleteById(id);
            rs.addFlashAttribute("message", "Cita eliminada correctamente");
        } catch (Exception e) {
            rs.addFlashAttribute("error", "No se puede eliminar la cita porque tiene tratamientos asociados.");
        }
        return "redirect:/view/citas";
    }
}
