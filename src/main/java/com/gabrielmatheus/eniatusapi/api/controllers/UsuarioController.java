package com.gabrielmatheus.eniatusapi.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gabrielmatheus.eniatusapi.domain.models.usuario.Usuario;
import com.gabrielmatheus.eniatusapi.domain.services.usuario.UsuarioService;
import com.gabrielmatheus.eniatusapi.domain.repositories.usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private UsuarioService usuarioService;

  @GetMapping
  public List<Usuario> index() {
    return usuarioRepository.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Usuario create (@Valid @RequestBody Usuario usuario) {
    return usuarioService.save(usuario);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Usuario> show(@PathVariable Long id) {
    Optional<Usuario> usuario = usuarioRepository.findById(id);

    if(!usuario.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(usuario.get());
  }
  
}
