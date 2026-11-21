package com.vetsys.vetsys.view;

import com.vetsys.vetsys.controller.MascotaController;
import com.vetsys.vetsys.dto.Mascotas.MascotasDTOEntrada;
import com.vetsys.vetsys.entity.Dueno;
import com.vetsys.vetsys.entity.Mascota;
import com.vetsys.vetsys.repository.DuenoRepository;
import com.vetsys.vetsys.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MascotasView {

    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private DuenoRepository duenoRepository;

    //metodo para utilizar los datos por medio del dto de entrada, que es que recibe datos
    private void construirMascota(Mascota mascota, MascotasDTOEntrada entrada)
    {
        Dueno dueno = entrada.getIdDueno() != null
                ? duenoRepository.findById(entrada.getIdDueno()).orElse(null)
                : null;

        mascota.setNombre(entrada.getNombre());
        mascota.setEspecie(entrada.getEspecie());
        mascota.setRaza(entrada.getRaza());
        mascota.setEdad(entrada.getEdad());
        mascota.setDueno(dueno);
    }

    @GetMapping("/view/mascotas")
    public String lista(Model model)
    {
        model.addAttribute("mascotas", mascotaRepository.findAll());
        return "mascotas/list";
    }

    @GetMapping("/view/mascotas/form")
    public String form(Model model)
    {
        model.addAttribute("mascota", new MascotasDTOEntrada());
        model.addAttribute("duenos", duenoRepository.findAll());
        return "mascotas/form";
    }

    @GetMapping("/view/mascotas/edit/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Mascota mascota = mascotaRepository.findById(id).orElse(null);
        if (mascota == null) { return "redirect:/view/mascotas"; }

        MascotasDTOEntrada entrada = new MascotasDTOEntrada();
        entrada.setId_mascota(mascota.getId_mascota());
        entrada.setNombre(mascota.getNombre());
        entrada.setEspecie(mascota.getEspecie());
        entrada.setRaza(mascota.getRaza());
        entrada.setEdad(mascota.getEdad());
        entrada.setIdDueno(mascota.getDueno() != null ? mascota.getDueno().getId_dueno() : null);

        model.addAttribute("mascota", entrada);
        model.addAttribute("duenos", duenoRepository.findAll());
        return "mascotas/form";
    }

    @PostMapping("/view/mascotas/save")
    public String save(@ModelAttribute("mascota") MascotasDTOEntrada entrada, RedirectAttributes rs) {
        boolean edicion = entrada.getId_mascota() != null;

        Mascota mascota = new Mascota();
        if (edicion) { mascota.setId_mascota(entrada.getId_mascota()); }
        construirMascota(mascota, entrada);

        mascotaRepository.save(mascota);
        String mensaje = edicion
                ? mascota.getNombre() + " se actualizo correctamente"
                : mascota.getNombre() + " se registró correctamente";

        rs.addFlashAttribute("message", mensaje);
        return "redirect:/view/mascotas";
    }

    @PostMapping("/view/mascotas/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rs)
    {
        mascotaRepository.deleteById(id);
        rs.addFlashAttribute("message", "Mascota eliminada correctamente");
        return "redirect:/view/mascotas";
    }


}
