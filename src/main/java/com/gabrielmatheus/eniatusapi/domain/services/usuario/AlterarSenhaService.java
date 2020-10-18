package com.gabrielmatheus.eniatusapi.domain.services.usuario;

import com.gabrielmatheus.eniatusapi.api.dto.SenhaDto;
import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.usuario.Usuario;
import com.gabrielmatheus.eniatusapi.domain.repositories.usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AlterarSenhaService {

  @Autowired
  UsuarioRepository usuarioRepository;

  // public Usuario alterarSenha(String login, String email, String senha, String novaSenha) {
  public Usuario alterarSenha(Long id, SenhaDto senhaDto) {

    Usuario usuario = usuarioRepository.findById(id)
      .orElseThrow(() -> new BusinessException("Usuário não encontrado"));
    
    String senhaCrypt = passwordEncoder().encode(senhaDto.getNovaSenha());

    System.out.println("Senha do usuario " + usuario.getSenha());
    System.out.println("Senha dto encode " + passwordEncoder().encode(senhaDto.getSenha()));
    System.out.println("Senha dto " + senhaDto.getSenha());
    System.out.println("Senha nova " + senhaCrypt);

    boolean isSenha = passwordEncoder().matches(senhaDto.getSenha(), usuario.getSenha());
    System.out.println(isSenha == true ? "Senha corresponde" : "Senha não corresponde");

    if(
      // !usuario.getSenha().equalsIgnoreCase(senhaDto.getSenha())
      // || 
      !passwordEncoder().matches(senhaDto.getSenha(), usuario.getSenha())
    ) {
      throw new BusinessException("Senha antiga incorreta");
    }

    // String senhaCrypt = Crypt.passwordEncoder().encode(senhaDto.getNovaSenha());
    System.out.println("Senha :" + senhaDto.getSenha() + "\nSenha crypto:" + senhaCrypt);
    
    // Pode ser que não seja preciso
    // usuario.setId(usuario.getId());

    usuario.setSenha(senhaCrypt);
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
