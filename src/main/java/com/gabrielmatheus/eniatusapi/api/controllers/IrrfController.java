package com.gabrielmatheus.eniatusapi.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gabrielmatheus.eniatusapi.domain.models.Irrf;
import com.gabrielmatheus.eniatusapi.domain.services.imposto.IrrfService;

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
@RequestMapping("/api/irrf")
public class IrrfController {

  @Autowired
  private IrrfService irrfService;

  @GetMapping
  public List<Irrf> index() {
    return irrfService.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Irrf create(@Valid @RequestBody Irrf i) {
    return irrfService.save(i);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Irrf> show(@PathVariable Long id) {
   
    Optional<Irrf> irrf = irrfService.findById(id);

    if(!irrf.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(irrf.get());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Irrf> update (@PathVariable Long id, @RequestBody Irrf i) {
    
    Irrf irrf = irrfService.update(i, id);

    if(irrf == null) {
      return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(irrf);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete (@PathVariable Long id) {
    
    if(!irrfService.delete(id)) {
      return ResponseEntity.notFound().build();
    }
   
    return ResponseEntity.noContent().build();
  }
  
}
