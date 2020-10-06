package com.gabrielmatheus.eniatusapi.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gabrielmatheus.eniatusapi.domain.models.FolhaMensal;
import com.gabrielmatheus.eniatusapi.domain.repositories.FolhaMensalRepository;
import com.gabrielmatheus.eniatusapi.domain.services.folha_mensal.FolhaMensalService;

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
@RequestMapping("/api/folha-de-pagamento")
public class FolhaMensalController {

  @Autowired
  private FolhaMensalRepository folhaMensalRepository;

  @Autowired
  private FolhaMensalService folhaMensalService;


  @GetMapping
  public List<FolhaMensal> index() {
    return folhaMensalRepository.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FolhaMensal create(@Valid @RequestBody FolhaMensal folhaMensal) {
    return folhaMensalService.save(folhaMensal);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FolhaMensal> show(@PathVariable Long id) {
    Optional<FolhaMensal> folhaMensal = folhaMensalRepository.findById(id);

    if (folhaMensal.isPresent()) {
      return ResponseEntity.ok(folhaMensal.get());
    }

    return ResponseEntity.notFound().build();

  }

  @PutMapping("/{id}")
  public ResponseEntity<FolhaMensal> update(@PathVariable Long id, @Valid @RequestBody FolhaMensal folha) {
    FolhaMensal folhaMensal = folhaMensalService.update(folha, id);

    if (folhaMensal== null)
      return ResponseEntity.notFound().build();

    return ResponseEntity.ok(folhaMensal);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!folhaMensalService.delete(id)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
  }
  
}
