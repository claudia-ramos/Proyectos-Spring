package com.example.AgendaContactos.servicio;

import com.example.AgendaContactos.dto.UsuarioRegistroDTO;
import com.example.AgendaContactos.entidad.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioServicio extends UserDetailsService {

    Usuario guardar(UsuarioRegistroDTO usuarioRegistroDTO);

    List<Usuario> listarTodos();
}
