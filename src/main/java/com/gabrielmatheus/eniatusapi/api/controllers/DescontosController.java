package com.gabrielmatheus.eniatusapi.api.controllers;

import java.util.List;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Descontos;
import com.gabrielmatheus.eniatusapi.domain.models.OutrosDescontos;
import com.gabrielmatheus.eniatusapi.domain.repositories.DescontosRepository;
// import com.gabrielmatheus.eniatusapi.domain.services.OutrosDescontosService;
// import com.gabrielmatheus.eniatusapi.domain.services.DescontoServices.CalcularDescontosServices;
import com.gabrielmatheus.eniatusapi.domain.services.DescontoServices.AddOutrosDescontosServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/descontos")
public class DescontosController {

  @Autowired
  private DescontosRepository descontosRepository;

  // @Autowired
  // private OutrosDescontosService outrosDescontosService;

  // @Autowired
  // private CalcularDescontosServices calcularDescontosServices;

  @Autowired
  private AddOutrosDescontosServices addOutrosDescontosServices;

  @GetMapping
  public List<Descontos> index() {
    return descontosRepository.findAll();
  }

  // @PostMapping
  // @ResponseStatus(HttpStatus.CREATED)
  // public OutrosDescontos create(@Valid @RequestBody OutrosDescontos od) {
  // return outrosDescontosService.save(od);
  // }

  @PostMapping("/{id}/outros-descontos/")
  public ResponseEntity<OutrosDescontos> create(@PathVariable Long id, @RequestBody OutrosDescontos outrosDescontos) {
    OutrosDescontos oa = addOutrosDescontosServices.addDescontos(id, outrosDescontos);

    if (oa == null) {
      return ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok().body(oa);
  }

  @GetMapping("/{id]")
  public ResponseEntity<Descontos> show(@PathVariable Long id) {
    Optional<Descontos> descontos = descontosRepository.findById(id);
    
    if(!descontos.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(descontos.get());
  }
}
