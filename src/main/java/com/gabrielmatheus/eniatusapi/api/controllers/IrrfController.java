package com.gabrielmatheus.eniatusapi.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gabrielmatheus.eniatusapi.domain.models.Irrf;
import com.gabrielmatheus.eniatusapi.domain.services.IrrfService;

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

  @GetMapping("/{irrfID}")
  public ResponseEntity<Irrf> show(@PathVariable Long irrfID) {
   
    Optional<Irrf> irrf = irrfService.findById(irrfID);

    if(!irrf.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(irrf.get());
  }

  @PutMapping("/{irrfID}")
  public ResponseEntity<Irrf> update (@PathVariable Long irrfID, @RequestBody Irrf i) {
    
    Irrf irrf = irrfService.update(i, irrfID);

    if(irrf == null) {
      return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(irrf);
  }

  @DeleteMapping("/{irrfID}")
  public ResponseEntity<Void> delete (@PathVariable Long irrfID) {
    
    if(!irrfService.delete(irrfID)) {
      return ResponseEntity.notFound().build();
    }
   
    return ResponseEntity.noContent().build();
  }
  
}
