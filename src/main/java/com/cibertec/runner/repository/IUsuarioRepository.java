package com.cibertec.runner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.runner.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{

    Optional<Usuario> findByMail(String mail);
}
