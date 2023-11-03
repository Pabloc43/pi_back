package com.grupo3.digitalbook.demo.service;

import com.grupo3.digitalbook.demo.entity.Dto.UsuarioRegistroDTO;
import com.grupo3.digitalbook.demo.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioServicio extends UserDetailsService {

    public Usuario guardar(UsuarioRegistroDTO registroDTO);

    public List<Usuario> listarUsuarios();

}
