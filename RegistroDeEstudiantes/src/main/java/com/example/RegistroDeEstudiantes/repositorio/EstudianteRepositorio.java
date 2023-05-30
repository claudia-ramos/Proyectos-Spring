package com.example.RegistroDeEstudiantes.repositorio;

import com.example.RegistroDeEstudiantes.entidad.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepositorio extends JpaRepository<Estudiante,Long> {
}
