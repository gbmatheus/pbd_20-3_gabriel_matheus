package com.gabrielmatheus.eniatusapi.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gabrielmatheus.eniatusapi.domain.models.OutrosDescontos;
import com.gabrielmatheus.eniatusapi.domain.services.descontos.OutrosDescontosService;

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
@RequestMapping("/api/outros-descontos")
public class OutrosDescontosController {
  
  @Autowired
  private OutrosDescontosService outrosDescontosService;

  @GetMapping
  public List<OutrosDescontos> index() {
    return outrosDescontosService.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OutrosDescontos create(@Valid @RequestBody OutrosDescontos od) {
    return outrosDescontosService.save(od);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OutrosDescontos> show(@PathVariable Long id) {
   
    Optional<OutrosDescontos> outrosDescontos = outrosDescontosService.findById(id);

    if(!outrosDescontos.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(outrosDescontos.get());
  }

  @PutMapping("/{id}")
  public ResponseEntity<OutrosDescontos> update (@PathVariable Long id, @RequestBody OutrosDescontos od) {
    
    OutrosDescontos outrosDescontos = outrosDescontosService.update(od, id);

    if(outrosDescontos == null) {
      return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(outrosDescontos);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete (@PathVariable Long id) {
    
    if(!outrosDescontosService.delete(id)) {
      return ResponseEntity.notFound().build();
    }
   
    return ResponseEntity.noContent().build();
  }
}
