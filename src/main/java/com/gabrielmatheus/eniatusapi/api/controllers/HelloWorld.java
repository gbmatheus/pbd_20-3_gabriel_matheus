package com.gabrielmatheus.eniatusapi.api.controllers;

import java.util.List;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.api.dto.SenhaDto;
import com.gabrielmatheus.eniatusapi.domain.models.usuario.Usuario;
import com.gabrielmatheus.eniatusapi.domain.repositories.usuario.UsuarioRepository;
// import com.gabrielmatheus.eniatusapi.domain.services.usuario.CadastroUsuario;
import com.gabrielmatheus.eniatusapi.domain.services.usuario.AlterarSenhaService;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/")
public class HelloWorld {

  @Autowired
  private UsuarioRepository repository;

  @Autowired
  private AlterarSenhaService alterarSenhaService;

  // @Autowired
  // private CadastroUsuario service;

  

  @GetMapping
  public ResponseEntity<String> Hello() {

    return ResponseEntity.ok().body("Hello World");

  }

  @GetMapping("/user/")
  public List<Usuario> Usuarios() {

    return repository.findAll();

  }

  // @ResponseStatus(HttpStatus.CREATED)
  // public Usuario create (@RequestBody Usuario usuario) {
  //   return service.save(usuario);
  // }

  @PostMapping("/conta/{id}/alterar-senha")
  public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody SenhaDto senhaDto) {
      System.out.println(senhaDto.getSenha());
      System.out.println(senhaDto.getNovaSenha());
      
      Usuario usuario = alterarSenhaService.alterarSenha(id, senhaDto);

      if(usuario == null)
        return ResponseEntity.notFound().build();

      return ResponseEntity.ok(usuario);
    }

  @GetMapping("/user/{user}")
  public ResponseEntity<Usuario> LoginUsuario(@PathVariable String user) {
    Optional<Usuario> usuario = repository.findByLogin(user);

    if(!usuario.isPresent()) {
      // throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(usuario.get());

  }
  
}
