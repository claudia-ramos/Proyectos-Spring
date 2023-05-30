package com.example.RegistroDeEstudiantes.controlador;

import com.example.RegistroDeEstudiantes.entidad.Estudiante;
import com.example.RegistroDeEstudiantes.servicio.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EstudianteControlador {

    @Autowired
    private EstudianteServicio estudianteServicio;

    @GetMapping(  {"/estudiantes","/"})
    public String listarEstudiantes(Model model) {
        List<Estudiante> lista = estudianteServicio.listarEstudiantes();
        model.addAttribute("estudiantes",lista);
        return "estudiante";
    }

    @GetMapping("/estudiantes/nuevo")
    public String crearEstudiante(Model model) {
        Estudiante estudiante = new Estudiante();
        model.addAttribute("estudiante",estudiante);
        return "crear_estudiante";
    }

    @PostMapping( "/estudiantes" )
    public String guardarEstudiante(@ModelAttribute("estudiante") Estudiante estudiante) {
        estudianteServicio.guardarEstudiante(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("estudiantes/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("estudiante",estudianteServicio.obtenerPorId(id));
        return "editar_estudiante";
    }

    @PostMapping("estudiantes/{id}")
    public String actualizarEstudiante(@PathVariable Long id, Estudiante estudiante) {
        Estudiante estudianteExistente = estudianteServicio.obtenerPorId(id);
        estudianteExistente.setId(id);
        estudianteExistente.setNombre(estudiante.getNombre());
        estudianteExistente.setApellido(estudiante.getApellido());
        estudianteExistente.setEmail(estudiante.getEmail());

        estudianteServicio.actualizarEstudiante(estudianteExistente);
        return "redirect:/estudiantes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable long id) {
        estudianteServicio.eliminarEstudiante(id);
        return "redirect:/estudiantes";
    }
}