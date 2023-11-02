package com.grupo3.digitalbook.demo.service.impl;

import com.grupo3.digitalbook.demo.entity.AppUsuario;
import com.grupo3.digitalbook.demo.entity.AppUsuarioRole;
import com.grupo3.digitalbook.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUsuarioService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUsuario> usuarioBuscado = userRepository.findByEmail(username);
        if (usuarioBuscado.isPresent()) {
            return usuarioBuscado.get();
        } else {
            throw new UsernameNotFoundException("Usuario con correo " + username + " no encontrado en la BD");
        }
    }

    public AppUsuario guardarUsuario(AppUsuario appUsuario) {
        return userRepository.save(appUsuario);
    }

    public AppUsuario registrarNuevoUsuario(AppUsuario nuevoUsuario) {
        // Verifica si el correo electrónico ya existe en la base de datos
        Optional<AppUsuario> usuarioExistente = userRepository.findByEmail(nuevoUsuario.getEmail());
        if (usuarioExistente.isPresent()) {
            // El correo electrónico ya está en uso
            return null;
        } else {
            // Cifra la contraseña antes de guardarla
            String contraseñaCifrada = bCryptPasswordEncoder.encode(nuevoUsuario.getPassword());
            nuevoUsuario.setPassword(contraseñaCifrada);

            // Define el rol del nuevo usuario (por ejemplo, ROLE_USER)
            nuevoUsuario.setAppUsuarioRole(AppUsuarioRole.ROLE_USER);

            // Guarda al nuevo usuario en la base de datos
            return userRepository.save(nuevoUsuario);
        }
    }
}

