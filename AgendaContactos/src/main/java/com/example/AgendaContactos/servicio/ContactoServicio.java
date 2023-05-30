package com.example.AgendaContactos.servicio;

import com.example.AgendaContactos.entidad.Contacto;

import java.util.List;

public interface ContactoServicio {

    List<Contacto> listarTodosLosContactos();

    Contacto guardarContacto(Contacto contacto);

    Contacto obtenerPorId(Integer id);

    Contacto actualizarContacto(Contacto contacto);

    void eliminarContacto(Contacto contacto);
}
