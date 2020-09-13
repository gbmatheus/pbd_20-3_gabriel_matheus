package com.gabrielmatheus.eniatusapi.domain.services;

import com.gabrielmatheus.eniatusapi.domain.models.OutrosDescontos;
import com.gabrielmatheus.eniatusapi.domain.repositories.OutrosDescontosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class OutrosDescontosService extends ServiceGeneric<OutrosDescontos> {

  @Autowired
  private OutrosDescontosRepository outrosDescontosRepository;

  @Override
  public JpaRepository<OutrosDescontos, Long> getRepository() {
    return outrosDescontosRepository;
  }

  @Override
  public OutrosDescontos update(OutrosDescontos outrosDescontos, Long id) {
    if(!getRepository().existsById(id)) {
      return null;
    }

    outrosDescontos.setId(id);
    return getRepository().save(outrosDescontos);
  }
  
  
}
