package com.gabrielmatheus.eniatusapi.domain.services.proventos;

import java.time.LocalDateTime;

import com.gabrielmatheus.eniatusapi.domain.models.OutrosAcrescimos;
import com.gabrielmatheus.eniatusapi.domain.repositories.OutrosAcrescimosRepository;
import com.gabrielmatheus.eniatusapi.domain.services.ServiceGeneric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

// Dúdida se realmente será necessário o service de salário familia
@Service
public class OutrosAcrescimosService extends ServiceGeneric<OutrosAcrescimos> {

  @Autowired
  private OutrosAcrescimosRepository outrosAcrescimosRepository;

  @Override
  public JpaRepository<OutrosAcrescimos, Long> getRepository() {
    return outrosAcrescimosRepository;
  }

  @Override
  public OutrosAcrescimos save(OutrosAcrescimos outrosAcrescimos) {
    outrosAcrescimos.setDataAcrecimo(LocalDateTime.now());
    return getRepository().save(outrosAcrescimos);
  }

  @Override
  public OutrosAcrescimos update(OutrosAcrescimos acrescimo, Long id) {
    if(!getRepository().existsById(id)) {
      return null;
    }
    
    acrescimo.setId(id);
    return getRepository().save(acrescimo);
  }
  
}
