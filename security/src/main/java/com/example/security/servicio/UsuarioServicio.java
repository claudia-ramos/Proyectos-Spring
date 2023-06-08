package com.example.security.servicio;

import com.example.security.dto.UsuarioRegistroDTO;

import com.example.security.modelo.Usuario;
import com.example.security.repositorio.UsuarioRepositorio;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioServicio extends UserDetailsService {

    Usuario guardar(UsuarioRegistroDTO usuarioRegistroDTO);

    List<Usuario> listarTodos();
}
