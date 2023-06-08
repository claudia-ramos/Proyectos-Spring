package com.example.Registro_Personas.repositorio;

import com.example.Registro_Personas.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
    public Usuario findByEmail(String email);
}
