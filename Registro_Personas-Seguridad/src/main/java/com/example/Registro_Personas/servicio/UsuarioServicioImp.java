package com.example.Registro_Personas.servicio;

import com.example.Registro_Personas.dto.UsuarioRegistroDTO;
import com.example.Registro_Personas.entidad.Rol;
import com.example.Registro_Personas.entidad.Usuario;
import com.example.Registro_Personas.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServicioImp implements UsuarioServicio {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public Usuario guardar(UsuarioRegistroDTO usuarioRegistroDTO) {
        Usuario usuario = new Usuario(usuarioRegistroDTO.getNombre(), usuarioRegistroDTO.getApellido(),
                usuarioRegistroDTO.getEmail(), passwordEncoder.encode(usuarioRegistroDTO.getPassword()), Arrays.asList(new Rol("ROLE_USER")));

        return usuarioRepositorio.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password invalidos");
        }
        return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepositorio.findAll();
    }
}