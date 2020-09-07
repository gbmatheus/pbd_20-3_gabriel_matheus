package com.gabrielmatheus.eniatusapi.api.controllers;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gabrielmatheus.eniatusapi.domain.models.OutrosAcrescimos;
import com.gabrielmatheus.eniatusapi.domain.services.OutrosAcrescimosService;

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
@RequestMapping("/api/outros-acrescimos")
public class OutrosAcrescimosController {
    
  @Autowired
  private OutrosAcrescimosService outrosAcrescimosService;

  @GetMapping
  public List<OutrosAcrescimos> index() {
    return outrosAcrescimosService.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OutrosAcrescimos create(@Valid @RequestBody OutrosAcrescimos oa) {
    OutrosAcrescimos outrosAcrescimos = outrosAcrescimosService.save(oa);

    return outrosAcrescimos;
  }

  @GetMapping("/{acrescimoID}")
  public ResponseEntity<OutrosAcrescimos> show(@PathVariable Long acrescimoID) {
    Optional<OutrosAcrescimos> OutrosAcrescimos = outrosAcrescimosService.findById(acrescimoID);

    if(!OutrosAcrescimos.isPresent()){
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(OutrosAcrescimos.get());
  }

  @PutMapping("/{acrescimoID}")
  public ResponseEntity<OutrosAcrescimos> update (@Valid @RequestBody OutrosAcrescimos oa,
    @PathVariable Long acrescimoID) {
    
    OutrosAcrescimos OutrosAcrescimos = outrosAcrescimosService.update(oa, acrescimoID);
    
    if(OutrosAcrescimos == null){
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(OutrosAcrescimos);
  }

  @DeleteMapping("/{acrescimoID}")
  public ResponseEntity<Void> delete (@PathVariable Long acrescimoID) {
    if(!outrosAcrescimosService.delete(acrescimoID)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
  }
  
}
