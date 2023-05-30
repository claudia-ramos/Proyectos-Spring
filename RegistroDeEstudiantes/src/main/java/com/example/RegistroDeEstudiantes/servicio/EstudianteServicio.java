package com.example.RegistroDeEstudiantes.servicio;

import com.example.RegistroDeEstudiantes.entidad.Estudiante;


import java.util.List;

public interface EstudianteServicio {

    public List<Estudiante> listarEstudiantes();

    public Estudiante guardarEstudiante(Estudiante estudiante);

    public Estudiante obtenerPorId(Long id);

    public Estudiante actualizarEstudiante(Estudiante estudiante);

    public void eliminarEstudiante(Long id);
}