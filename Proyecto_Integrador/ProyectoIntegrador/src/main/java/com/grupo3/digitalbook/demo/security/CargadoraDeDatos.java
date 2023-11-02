package com.grupo3.digitalbook.demo.security;

import com.grupo3.digitalbook.demo.entity.AppUsuario;
import com.grupo3.digitalbook.demo.entity.AppUsuarioRole;
import com.grupo3.digitalbook.demo.service.impl.AppUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {
    @Autowired
    private AppUsuarioService appUsuarioService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "admin";
        String passwordCifrada = passwordEncoder.encode(password);
        appUsuarioService.guardarUsuario(new AppUsuario("Diego de la Barrera", "diego@gmail.com", passwordCifrada, AppUsuarioRole.ROLE_ADMIN));
    }
}
