package com.gabrielmatheus.eniatusapi.domain.services.usuario;

import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.Pessoa;
import com.gabrielmatheus.eniatusapi.domain.models.usuario.Usuario;
import com.gabrielmatheus.eniatusapi.domain.repositories.PessoaRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final PessoaRepository pessoaRepository;

  @Autowired
  public UsuarioService(UsuarioRepository usuarioRepository, PessoaRepository pessoaRepository) {
    this.usuarioRepository = usuarioRepository;
    this.pessoaRepository = pessoaRepository;
  }

  public Usuario save(Usuario usuario) {
    // Optional<Usuario> u = usuarioRepository.findByLogin(usuario.getLogin());
    Pessoa pessoa = createPessoa(usuario.getPessoa());

    Optional<Usuario> usuarioPessoa = usuarioRepository.findByPessoa(pessoa);
    
    if(usuarioPessoa.isPresent()) {
      throw new BusinessException("Cpf já utilizado");
    }

    Optional<Usuario> u = usuarioRepository.findByLoginOrEmail(usuario.getLogin(), usuario.getEmail());

    if(u.isPresent()) {
      throw new BusinessException("Usuário já existente");
    }

    String senha = passwordEncoder().encode(usuario.getSenha());
    System.out.println(senha);
    usuario.setSenha(senha);
    usuario.setPessoa(pessoa);

    return usuarioRepository.save(usuario);
  }

  public Usuario update(Usuario usuario, Long id) {
    if(!usuarioRepository.existsById(id)) {
      throw new BusinessException("Usuário não existe");
    }

    String senha = passwordEncoder().encode(usuario.getSenha());
    System.out.println(senha);
    usuario.setSenha(senha);

    usuario.setId(id);
    return usuarioRepository.save(usuario);
  }


  public Pessoa createPessoa(Pessoa pessoa) {
    // verificar se a pessoa existe, e o cpf dela
    Optional<Pessoa> p = pessoaRepository.findByCpf(pessoa.getCpf());

    if (p.isPresent()) {
      return p.get();
    }

    return pessoaRepository.save(pessoa);

  }

  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
}
