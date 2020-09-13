package com.gabrielmatheus.eniatusapi.domain.services;

import com.gabrielmatheus.eniatusapi.domain.models.OutrosAcrescimos;
import com.gabrielmatheus.eniatusapi.domain.repositories.OutrosAcrescimosRepository;

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
  public OutrosAcrescimos update(OutrosAcrescimos oa, Long id) {

    if(!getRepository().existsById(id)) {
      return null;
    }
    
    oa.setId(id);
    return getRepository().save(oa);
  }
  
}
