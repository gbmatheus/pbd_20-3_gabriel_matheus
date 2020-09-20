package com.gabrielmatheus.eniatusapi.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gabrielmatheus.eniatusapi.domain.models.Funcionario;
import com.gabrielmatheus.eniatusapi.domain.services.FuncionarioService;
import com.gabrielmatheus.eniatusapi.domain.services.FuncionarioServices.CadastroFuncionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

  @Autowired
  private FuncionarioService funcionarioService;

  @Autowired
  private CadastroFuncionario cadastroFuncionarioService;

  @GetMapping
  public List<Funcionario> index() {
    return funcionarioService.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Funcionario create(@Valid @RequestBody Funcionario f) {
    Funcionario funcionario = cadastroFuncionarioService.create(f);

    if(funcionario == null) {
      System.out.println("Funcionario nulo");
    }

    return funcionario;
    // return funcionarioService.save(f);
  }

  @GetMapping("/{funcionadioID}")
  public ResponseEntity<Funcionario> show(@PathVariable Long funcionarioID) {
    Optional<Funcionario> funcionario = funcionarioService.findById(funcionarioID);

    if (funcionario.isPresent()) {
      return ResponseEntity.ok(funcionario.get());
    }

    return ResponseEntity.notFound().build();

  }  

  @PutMapping("/{funcionarioID}")
  public ResponseEntity<Funcionario> update(@PathVariable Long funcionarioID,
    @RequestBody Funcionario f) {
      
      Funcionario funcionario = funcionarioService.update(f, funcionarioID);

      if(funcionario == null)
        return ResponseEntity.notFound().build();

      return ResponseEntity.ok(funcionario);
    }

  @DeleteMapping("/{funcionarioID}")
  public ResponseEntity<Void> delete (Long funcionarioID) {
    if(!funcionarioService.delete(funcionarioID)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
  }
  
}
