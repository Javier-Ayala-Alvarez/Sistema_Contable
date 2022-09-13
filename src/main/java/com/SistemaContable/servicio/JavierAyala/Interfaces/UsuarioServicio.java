package com.SistemaContable.servicio.JavierAyala.Interfaces;

import com.SistemaContable.model.dto.UsuarioRegistroDTO;
import com.SistemaContable.model.JavierAyala.Usuario;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UsuarioServicio extends UserDetailsService {

    public Usuario guardar(UsuarioRegistroDTO registroDTO);

    public List<Usuario> listarUsuarios();

}
