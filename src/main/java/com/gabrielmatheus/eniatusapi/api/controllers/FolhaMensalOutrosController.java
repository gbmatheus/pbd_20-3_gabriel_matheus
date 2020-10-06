package com.gabrielmatheus.eniatusapi.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gabrielmatheus.eniatusapi.domain.models.FolhaMensal;
import com.gabrielmatheus.eniatusapi.domain.models.OutrosDescontos;
import com.gabrielmatheus.eniatusapi.domain.repositories.FolhaMensalRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.OutrosDescontosRepository;
import com.gabrielmatheus.eniatusapi.domain.services.descontos.AdicionarDescontosService;
import com.gabrielmatheus.eniatusapi.domain.models.OutrosAcrescimos;
import com.gabrielmatheus.eniatusapi.domain.repositories.OutrosAcrescimosRepository;
import com.gabrielmatheus.eniatusapi.domain.services.proventos.AdicionarAcrescimosService;

import org.springframework.beans.factory.annotation.Autowired;
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
public class FolhaMensalOutrosController {

  private final FolhaMensalRepository folhaMensalRepository;
  private final OutrosDescontosRepository outrosDescontosRepository;
  private final AdicionarDescontosService addOutrosDescontosServices;
  private final OutrosAcrescimosRepository outrosAcrescimosRepository;
  private final AdicionarAcrescimosService addAcrescimosService;

  @Autowired
  public FolhaMensalOutrosController(FolhaMensalRepository folhaMensalRepository,
      OutrosDescontosRepository outrosDescontosRepository, AdicionarDescontosService addOutrosDescontosServices,
      OutrosAcrescimosRepository outrosAcrescimosRepository, AdicionarAcrescimosService addAcrescimosService) {
    this.folhaMensalRepository = folhaMensalRepository;
    this.outrosDescontosRepository = outrosDescontosRepository;
    this.addOutrosDescontosServices = addOutrosDescontosServices;
    this.outrosAcrescimosRepository = outrosAcrescimosRepository;
    this.addAcrescimosService = addAcrescimosService;
  }

  /**
   * 
   */
  @GetMapping("/{id}/outros-descontos")
  public ResponseEntity<List<OutrosDescontos>> indexOutrosDescontos(@PathVariable Long id) {
    Optional<FolhaMensal> folhaMensal = folhaMensalRepository.findById(id);

    if (!folhaMensal.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    List<OutrosDescontos> folhaOutrosDescontos = outrosDescontosRepository.findByFolhaMensal(folhaMensal.get());

    return ResponseEntity.ok(folhaOutrosDescontos);
  }

  @PostMapping("/{id}/outros-descontos")
  @ResponseStatus(HttpStatus.CREATED)
  public OutrosDescontos createOutrosDescontos(@PathVariable Long id,
      @Valid @RequestBody OutrosDescontos outrosDescontos) {
    return addOutrosDescontosServices.addDescontos(id, outrosDescontos);
  }

  /**
   * 
   */
  @GetMapping("/{id}/outros-acrescimos")
  public ResponseEntity<List<OutrosAcrescimos>> indexOutrosAcrescimos(@PathVariable Long id) {
    Optional<FolhaMensal> folhaMensal = folhaMensalRepository.findById(id);

    if (!folhaMensal.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    List<OutrosAcrescimos> proventoAcrescimos = outrosAcrescimosRepository.findByFolhaMensal(folhaMensal.get());

    return ResponseEntity.ok(proventoAcrescimos);
  }

  @PostMapping("/{id}/outros-acrescimos")
  @ResponseStatus(HttpStatus.CREATED)
  public OutrosAcrescimos createOutrosAcrescimos(@PathVariable Long id,
      @Valid @RequestBody OutrosAcrescimos outrosAcrescimos) {
    return addAcrescimosService.addAcrescimos(id, outrosAcrescimos);
  }

}
