package com.gabrielmatheus.eniatusapi.domain.repositories;

import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
  Optional<Usuario> findByLogin (String login);

}
