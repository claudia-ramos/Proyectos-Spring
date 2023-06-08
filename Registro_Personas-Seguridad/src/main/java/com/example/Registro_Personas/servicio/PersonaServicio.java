package com.example.Registro_Personas.servicio;

import com.example.Registro_Personas.entidad.Persona;

import java.util.List;

public interface PersonaServicio {

    List<Persona> listarTodasLasPersonas();

    Persona guardarPersona(Persona persona);

    Persona obtenerPorId(Integer id);

    Persona actualizarPersona(Persona persona);

    void eliminarPersona(Integer id);
}
