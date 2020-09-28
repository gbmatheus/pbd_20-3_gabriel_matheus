package com.gabrielmatheus.eniatusapi.domain.repositories.usuario;

import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.usuario.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
  Optional<Usuario> findByLogin (String login);

  Optional<Usuario> findByLoginOrEmail (String login, String email);

  Optional<Usuario> findByEmail (String email);
  
}
