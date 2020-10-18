package com.gabrielmatheus.eniatusapi.api.configs;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.gabrielmatheus.eniatusapi.domain.models.Pessoa;
import com.gabrielmatheus.eniatusapi.domain.models.enums.TipoPermissao;
import com.gabrielmatheus.eniatusapi.domain.models.usuario.Permissao;
import com.gabrielmatheus.eniatusapi.domain.models.usuario.Usuario;
import com.gabrielmatheus.eniatusapi.domain.repositories.PessoaRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.usuario.PermissaoRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

  private final UsuarioRepository usuarioRepository;
  private final PessoaRepository pessoaRepository;
  private final PermissaoRepository permissaoRepository;

  @Autowired
  public DataInitializr(UsuarioRepository usuarioRepository, PessoaRepository pessoaRepository,
      PermissaoRepository permissaoRepository) {
    this.usuarioRepository = usuarioRepository;
    this.pessoaRepository = pessoaRepository;
    this.permissaoRepository = permissaoRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {

    List<Usuario> usuarios = usuarioRepository.findAll();

    if (usuarios.isEmpty()) {
      createUser("admin", "admin@mailcom", new BCryptPasswordEncoder().encode("admin"), TipoPermissao.ADMIN,
          "000.000.000-00");
      createUser("contador", "contador@email.com", new BCryptPasswordEncoder().encode("contador"), TipoPermissao.CONTADOR,
          "000.000.000-01");
    }

  }

  public void createUser(String login, String email, String senha, String tipo, String cpf) {
    Pessoa pessoa = new Pessoa();
    pessoa.setCpf(cpf);
    pessoa.setDt_nascimento(LocalDateTime.now());
    pessoa.setNome(login);
    pessoa.setNaturalidade(Locale.getDefault().getDisplayLanguage());

    Permissao permissao = new Permissao(tipo);

    Pessoa pessoaSave = pessoaRepository.save(pessoa);
    Permissao permisaoSave = permissaoRepository.save(permissao);

    Usuario usuario = new Usuario();
    usuario.setLogin(login);
    usuario.setEmail(email);
    usuario.setSenha(senha);
    usuario.setPessoa(pessoaSave);
    usuario.setPermissoes(Arrays.asList(permisaoSave));

    usuarioRepository.save(usuario);

  }
}
