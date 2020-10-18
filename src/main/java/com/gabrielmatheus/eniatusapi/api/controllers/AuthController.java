package com.gabrielmatheus.eniatusapi.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.gabrielmatheus.eniatusapi.api.dto.UsuarioDto;
import com.gabrielmatheus.eniatusapi.domain.models.usuario.Usuario;
import com.gabrielmatheus.eniatusapi.domain.repositories.usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  private UsuarioRepository repository;

  @GetMapping("/api/login")
  public ResponseEntity<Usuario> getLogin() {
    UsuarioDto usuarioDto= new UsuarioDto("admin","admin");
    System.out.println(usuarioDto.toString());

    Optional<Usuario> usuario = repository.findByLogin(usuarioDto.getLogin());


    if (!usuario.isPresent() || passwordEncoder().matches(usuarioDto.getSenha(), usuario.get().getSenha())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    return ResponseEntity.accepted().body(usuario.get());

  }

  @PostMapping("/api/login")
  public ResponseEntity<Usuario> login(@Valid @RequestBody UsuarioDto usuarioDto) {
    Optional<Usuario> usuario = repository.findByLogin(usuarioDto.getLogin());

    if (!usuario.isPresent()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    if(!passwordEncoder().matches(usuarioDto.getSenha(), usuario.get().getSenha())){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    return ResponseEntity.accepted().body(usuario.get());

  }

  public PasswordEncoder passwordEncoder () {
    return new BCryptPasswordEncoder();
  }

}
