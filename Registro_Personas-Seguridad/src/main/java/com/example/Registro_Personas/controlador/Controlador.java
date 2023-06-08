package com.example.Registro_Personas.controlador;


import com.example.Registro_Personas.entidad.Persona;
import com.example.Registro_Personas.repositorio.PersonaRepositorio;
import com.example.Registro_Personas.servicio.PersonaServicio;
import com.example.Registro_Personas.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Controlador {

    @Autowired
    private PersonaServicio personaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;


    @GetMapping("/login")
    public String iniciarSesion(){
        return "login";
    }

    @GetMapping("/")
    public String verPaginaDeInicio(Model modelo){
        modelo.addAttribute("usuarios", usuarioServicio.listarTodos());
        return "index";
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Persona> personas = personaServicio.listarTodasLasPersonas();

        model.addAttribute("personas",personas);
        return "index";

    }

    @GetMapping("/new")
    public String agregar(Model model) {
        Persona persona = new Persona();

        model.addAttribute("persona",persona);
        return "crear_persona";

    }
    @PostMapping("/save")
    public String guardar(@ModelAttribute("persona") Persona persona) {
        personaServicio.guardarPersona(persona);
        return "redirect:/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Integer id) {
        personaServicio.eliminarPersona(id);
        return "redirect:/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarElFormularioDeEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("persona",personaServicio.obtenerPorId(id));
        return "editar_persona";
    }

    @PostMapping("/editar/{id}")
    public String actualizarPersona(@PathVariable Integer id, @ModelAttribute("persona") Persona persona ) {
        Persona personaExistente = personaServicio.obtenerPorId(id);
        personaExistente.setId(id);
        personaExistente.setNombre(persona.getNombre());
        personaExistente.setTelefono(persona.getTelefono());

        personaServicio.actualizarPersona(personaExistente);

        return "redirect:/listar";
    }
}
