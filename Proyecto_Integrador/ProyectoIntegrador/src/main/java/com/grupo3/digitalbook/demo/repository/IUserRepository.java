package com.grupo3.digitalbook.demo.repository;

import com.grupo3.digitalbook.demo.entity.AppUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<AppUsuario, Long> {
    Optional<AppUsuario> findByEmail(String email);
}
