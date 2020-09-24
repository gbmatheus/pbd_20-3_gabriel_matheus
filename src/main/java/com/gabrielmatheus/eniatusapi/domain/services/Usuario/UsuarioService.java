package com.gabrielmatheus.eniatusapi.domain.services.Usuario;

import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Usuario;
import com.gabrielmatheus.eniatusapi.domain.repositories.UsuarioRepository;
import com.gabrielmatheus.eniatusapi.domain.services.ServiceGeneric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends ServiceGeneric<Usuario> {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public JpaRepository<Usuario, Long> getRepository() {
    return usuarioRepository;
  }

  @Override
  public Usuario save(Usuario usuario) {
    Optional<Usuario> u = usuarioRepository.findByLogin(usuario.getLogin());

    if(u.isPresent()) {
      return null;
    }
    String senha = passwordEncoder().encode(usuario.getSenha());
    usuario.setSenha(senha);

    return save(usuario);
  }

  @Override
  public Usuario update(Usuario usuario, Long id) {
    if(!getRepository().existsById(id)) {
      return null;
    }

    usuario.setId(id);
    return getRepository().save(usuario);
  }

  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
}
