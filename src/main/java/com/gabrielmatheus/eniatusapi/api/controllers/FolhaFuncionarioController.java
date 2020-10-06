package com.gabrielmatheus.eniatusapi.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gabrielmatheus.eniatusapi.domain.models.FolhaMensal;
import com.gabrielmatheus.eniatusapi.domain.models.Funcionario;
import com.gabrielmatheus.eniatusapi.domain.repositories.FolhaMensalRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.FuncionarioRepository;
import com.gabrielmatheus.eniatusapi.domain.services.folha_mensal.FolhaDoFuncionarioService;

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
@RequestMapping("/api/folha-de-pagamento")
public class FolhaFuncionarioController {

  private final FuncionarioRepository funcionarioRepository;
  private final FolhaMensalRepository folhaMensalRepository;
  private final FolhaDoFuncionarioService folhaDoFuncionarioService;

  public FolhaFuncionarioController(FuncionarioRepository funcionarioRepository,
  FolhaMensalRepository folhaMensalRepository, FolhaDoFuncionarioService folhaDoFuncionarioService) {
    this.funcionarioRepository = funcionarioRepository;
    this.folhaMensalRepository = folhaMensalRepository;
    this.folhaDoFuncionarioService = folhaDoFuncionarioService;
  }
  
  @GetMapping("/funcionarios/{id}")
  public ResponseEntity<List<FolhaMensal>> indexFolhasFuncionario (@PathVariable Long id) {
    Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
      
    if(!funcionario.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    List<FolhaMensal> folhasDoFuncionairo = folhaMensalRepository.findByFuncionario(funcionario.get());

    return ResponseEntity.ok().body(folhasDoFuncionairo);

  }  

  @PostMapping("/funcionarios/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public FolhaMensal create(@PathVariable Long id, @Valid @RequestBody FolhaMensal folhaMensal ) {
     
    FolhaMensal folha = folhaDoFuncionarioService.createFolha(id, folhaMensal);
    
    System.out.println(folha.toString());
    
    return folha;
  }
  
}
