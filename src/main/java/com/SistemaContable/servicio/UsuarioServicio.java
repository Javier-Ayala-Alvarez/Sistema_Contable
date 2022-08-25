package com.SistemaContable.servicio;

import com.SistemaContable.Controller.dto.UsuarioRegistroDTO;
import com.SistemaContable.model.Usuario;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UsuarioServicio extends UserDetailsService {

    public Usuario guardar(UsuarioRegistroDTO registroDTO);

    public List<Usuario> listarUsuarios();

}
