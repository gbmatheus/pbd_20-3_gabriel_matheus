package com.gabrielmatheus.eniatusapi.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gabrielmatheus.eniatusapi.domain.models.SalarioFamilia;
import com.gabrielmatheus.eniatusapi.domain.services.salarios.SalarioFamiliaService;

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
@RequestMapping("/api/salario-familia")
public class SalarioFamiliaController {
  
  @Autowired
  private SalarioFamiliaService salarioFamiliaService;

  @GetMapping
  public List<SalarioFamilia> index() {
    return salarioFamiliaService.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SalarioFamilia create(@Valid @RequestBody SalarioFamilia sf) {
    SalarioFamilia salarioFamilia = salarioFamiliaService.save(sf);

    return salarioFamilia;
  }

  @GetMapping("/{id}")
  public ResponseEntity<SalarioFamilia> show(@PathVariable Long id) {
    Optional<SalarioFamilia> salarioFamilia = salarioFamiliaService.findById(id);

    if(!salarioFamilia.isPresent()){
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(salarioFamilia.get());
  }

  @PutMapping("/{id}")
  public ResponseEntity<SalarioFamilia> update (@Valid @RequestBody SalarioFamilia sf,
    @PathVariable Long id) {
    
    SalarioFamilia salarioFamilia = salarioFamiliaService.update(sf, id);
    
    if(salarioFamilia == null){
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(salarioFamilia);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete (@PathVariable Long id) {
    if(!salarioFamiliaService.delete(id)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
  }
  
}
