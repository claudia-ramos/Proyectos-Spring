package com.example.AgendaContactos.controlador;

import com.example.AgendaContactos.entidad.Contacto;
import com.example.AgendaContactos.servicio.ContactoServicio;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactoControlador {
    @Autowired
    private ContactoServicio contactoServicio;

    @GetMapping("/")
    public String verPaginaDeInicio(Model model) {
        List<Contacto> contactos = contactoServicio.listarTodosLosContactos();
        model.addAttribute("contactos",contactos);
        return "index";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "nuevo";
    }

    @PostMapping("/save")
    public String guardarContacto(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("contacto",contacto);
            return "nuevo";
        }
        contactoServicio.guardarContacto(contacto);
        redirectAttributes.addFlashAttribute("msgExito","El contacto ha sido agregado con exito");
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Integer id,Model model) {
        Contacto contacto = contactoServicio.obtenerPorId(id);
        model.addAttribute("contacto",contacto);
        return "editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizarContacto(@PathVariable Integer id, @Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        Contacto contactoDB = contactoServicio.obtenerPorId(id);
        if(bindingResult.hasErrors()) {
            model.addAttribute("contacto",contacto);
            return "editar";
        }
        contactoDB.setId(id);
        contactoDB.setNombre(contacto.getNombre());
        contactoDB.setCelular(contacto.getCelular());
        contactoDB.setEmail(contacto.getEmail());
        contactoDB.setFechaNacimiento(contacto.getFechaNacimiento());

        contactoServicio.actualizarContacto(contactoDB);

        redirectAttributes.addFlashAttribute("msgExito","El contacto ha sido actualizado exitosamente");
        return "redirect:/";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Contacto contacto = contactoServicio.obtenerPorId(id);
        contactoServicio.eliminarContacto(contacto);
        redirectAttributes.addFlashAttribute("msgExito","El contacto ha sido eliminado exitosamente");
        return "redirect:/";
    }
}
