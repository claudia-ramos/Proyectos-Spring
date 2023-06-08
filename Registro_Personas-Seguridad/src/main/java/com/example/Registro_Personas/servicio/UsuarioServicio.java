package com.example.Registro_Personas.servicio;

import com.example.Registro_Personas.dto.UsuarioRegistroDTO;
import com.example.Registro_Personas.entidad.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioServicio extends UserDetailsService {

    Usuario guardar(UsuarioRegistroDTO usuarioRegistroDTO);

    List<Usuario> listarTodos();
}
