package com.gabrielmatheus.eniatusapi.api.controllers;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gabrielmatheus.eniatusapi.domain.models.Inss;
import com.gabrielmatheus.eniatusapi.domain.services.imposto.InssService;

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
@RequestMapping("/api/inss")
public class InssController {

  @Autowired
  private InssService inssService;

  @GetMapping
  public List<Inss> index() {
    return inssService.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Inss create(@Valid @RequestBody Inss i) {
    return inssService.save(i);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Inss> show(@PathVariable Long id) {
   
    Optional<Inss> inss = inssService.findById(id);

    if(!inss.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(inss.get());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Inss> update (@PathVariable Long id, @RequestBody Inss i) {
    
    Inss inss = inssService.update(i, id);

    if(inss == null) {
      return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(inss);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete (@PathVariable Long id) {
    
    if(!inssService.delete(id)) {
      return ResponseEntity.notFound().build();
    }
   
    return ResponseEntity.noContent().build();
  }
}
