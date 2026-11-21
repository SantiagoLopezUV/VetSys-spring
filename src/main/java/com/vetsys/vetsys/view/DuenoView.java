package com.vetsys.vetsys.view;


import com.vetsys.vetsys.entity.Dueno;
import com.vetsys.vetsys.repository.DuenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DuenoView {

    @Autowired
    private DuenoRepository duenoRepository;

    @GetMapping("/view/duenos")
    public String lista(Model model)
    {
        model.addAttribute("duenos", duenoRepository.findAll());
        return "duenos/list";
    }

    @GetMapping("/view/duenos/form")
    public String form(Model model)
    {
        model.addAttribute("duenos", new Dueno());
        return "duenos/form";
    }

    @GetMapping("/view/duenos/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Dueno duenos = duenoRepository.findById(id).orElse(null);

        if(duenos == null){ return "redirect:/view/duenos"; }

        model.addAttribute("duenos", duenos);
        return "duenos/form";
    }

    @PostMapping("/view/duenos/save")
    public String save(@ModelAttribute Dueno duenos, RedirectAttributes rs)
    {
        boolean edicion = duenos.getId_dueno() != null;
        duenoRepository.save(duenos);

        String mensaje = edicion
                ? duenos.getNombre() + " se actualizo correctamente"
                : duenos.getNombre() + " se registró correctamente";
        rs.addAttribute("message", mensaje);
        return "redirect:/view/duenos";
    }

    @PostMapping("/view/duenos/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rs) {
        duenoRepository.deleteById(id);
        rs.addFlashAttribute("messsage", "Dueño eliminado correctamente");
        return "redirect:/view/duenos";
    }
}
