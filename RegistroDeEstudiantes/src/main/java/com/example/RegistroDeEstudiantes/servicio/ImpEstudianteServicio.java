package com.example.RegistroDeEstudiantes.servicio;

import com.example.RegistroDeEstudiantes.entidad.Estudiante;
import com.example.RegistroDeEstudiantes.repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpEstudianteServicio implements EstudianteServicio {
    @Autowired
    private EstudianteRepositorio repositorio;
    @Override
    public List<Estudiante> listarEstudiantes() {
        return repositorio.findAll();
    }


    @Override
    public Estudiante guardarEstudiante(Estudiante estudiante) {
        return repositorio.save(estudiante);
    }

    @Override
    public Estudiante obtenerPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Estudiante actualizarEstudiante(Estudiante estudiante) {
        return repositorio.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Long id) {
        repositorio.deleteById(id);
    }
}