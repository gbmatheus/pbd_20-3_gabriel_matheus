package com.gabrielmatheus.eniatusapi.domain.services.usuario;

import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.usuario.Usuario;
import com.gabrielmatheus.eniatusapi.domain.repositories.usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  public Usuario save(Usuario usuario) {
    // Optional<Usuario> u = usuarioRepository.findByLogin(usuario.getLogin());
    Optional<Usuario> u = usuarioRepository.findByLoginOrEmail(usuario.getLogin(), usuario.getEmail());

    if(u.isPresent()) {
      throw new BusinessException("Usuário já existente");
    }

    String senha = passwordEncoder().encode(usuario.getSenha());
    System.out.println(senha);
    usuario.setSenha(senha);

    return usuarioRepository.save(usuario);
  }

  public Usuario update(Usuario usuario, Long id) {
    if(!usuarioRepository.existsById(id)) {
      throw new BusinessException("Usuário não existe");
    }

    usuario.setId(id);
    return usuarioRepository.save(usuario);
  }

  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
}
