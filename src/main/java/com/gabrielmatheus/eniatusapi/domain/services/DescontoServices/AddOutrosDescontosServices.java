package com.gabrielmatheus.eniatusapi.domain.services.DescontoServices;

import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Descontos;
import com.gabrielmatheus.eniatusapi.domain.models.OutrosDescontos;
import com.gabrielmatheus.eniatusapi.domain.repositories.DescontosRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.OutrosDescontosRepository;

import org.springframework.stereotype.Service;

@Service
public class AddOutrosDescontosServices {

  private final DescontosRepository descontosRepository;
  private final OutrosDescontosRepository outrosDescontosRepository;

  public AddOutrosDescontosServices(DescontosRepository descontosRepository,
      OutrosDescontosRepository outrosDescontosRepository) {
    this.descontosRepository = descontosRepository;
    this.outrosDescontosRepository = outrosDescontosRepository;
  }

  public OutrosDescontos addDescontos(Long descontosID, OutrosDescontos outrosDescontos) {
    Optional<Descontos> descontos = descontosRepository.findById(descontosID);

    if(!descontos.isPresent()) {
      return null;
    }
    
    outrosDescontos.setDescontos(descontos.get());
    return outrosDescontosRepository.save(outrosDescontos);
  }
  
}
