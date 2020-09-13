package com.gabrielmatheus.eniatusapi.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gabrielmatheus.eniatusapi.domain.models.SalarioMinimo;
import com.gabrielmatheus.eniatusapi.domain.services.SalarioMinimoService;

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
@RequestMapping("/api/salario-minimo")
public class SalarioMinimoController {

  @Autowired
  private SalarioMinimoService salarioMinimoService;

  @GetMapping
  public List<SalarioMinimo> index() {
    return salarioMinimoService.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SalarioMinimo create(@Valid @RequestBody SalarioMinimo sm) {
    SalarioMinimo salarioMinimo = salarioMinimoService.save(sm);

    return salarioMinimo;
  }

  @GetMapping("/{salarioID}")
  public ResponseEntity<SalarioMinimo> show(@PathVariable Long salarioID) {
    Optional<SalarioMinimo> salarioMinimo = salarioMinimoService.findById(salarioID);

    if(!salarioMinimo.isPresent()){
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(salarioMinimo.get());
  }

  @PutMapping("/{salarioID}")
  public ResponseEntity<SalarioMinimo> update (@Valid @RequestBody SalarioMinimo sm,
    @PathVariable Long salarioID) {
    
    SalarioMinimo salarioMinimo = salarioMinimoService.update(sm, salarioID);
    
    if(salarioMinimo == null){
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(salarioMinimo);
  }

  @DeleteMapping("/{salarioID}")
  public ResponseEntity<Void> delete (@PathVariable Long salarioID) {
    if(!salarioMinimoService.delete(salarioID)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
  }
  
}
