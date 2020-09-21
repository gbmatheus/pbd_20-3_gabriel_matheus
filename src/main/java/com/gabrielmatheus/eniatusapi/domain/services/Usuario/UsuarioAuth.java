package com.gabrielmatheus.eniatusapi.domain.services.Usuario;

import com.gabrielmatheus.eniatusapi.domain.models.Usuario;
import com.gabrielmatheus.eniatusapi.domain.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAuth {

  @Autowired
  UsuarioRepository usuarioRepository;

  public Usuario autenticacao(String login, String senha) {
    Usuario usuario = usuarioRepository.findByLogin(login)
      .orElse(null);

    String encodePassword = passwordEncoder().encode(usuario.getSenha());

    if(!passwordEncoder().matches(senha, encodePassword)) {
      return null;
    }

    return usuario;
    
  }

  public Boolean autorizacao(Usuario usuario, String tipo) {
    if(!usuario.getTipo().equalsIgnoreCase(tipo)) {
      return false;
    }
    return true;
  }

  public Usuario alterarSenha(String login, String email, String senha) {
    Usuario usuario = usuarioRepository.findByLogin(login)
      .orElse(null);

    // Pode ser que não seja preciso
    usuario.setId(usuario.getId());
    usuario.setSenha(senha);
    return usuarioRepository.save(usuario);
    
  }

  public Usuario recuperarSenha(String login, String email) {
    Usuario usuario = usuarioRepository.findByLogin(login)
      .orElse(null);

    // Pode ser que não seja preciso
    usuario.setId(usuario.getId());
    return usuarioRepository.save(usuario);
  }
  
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
