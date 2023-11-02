        package com.grupo3.digitalbook.demo.controller;

        import com.grupo3.digitalbook.demo.entity.AppUsuario;
        import com.grupo3.digitalbook.demo.service.impl.AppUsuarioService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        @RestController
        @RequestMapping("/api/registro")
        public class RegistroController {

            @Autowired
            private AppUsuarioService appUsuarioService;

            @PostMapping
            public String registrarUsuario(@RequestBody AppUsuario appUsuario) {
                AppUsuario nuevoUsuario = appUsuarioService.registrarNuevoUsuario(appUsuario);
                if (nuevoUsuario != null) {
                    return "Usuario registrado con éxito";
                } else {
                    return "Error al registrar el usuario";
                }
            }
        }

