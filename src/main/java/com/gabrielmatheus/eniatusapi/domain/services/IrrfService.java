package com.gabrielmatheus.eniatusapi.domain.services;

import com.gabrielmatheus.eniatusapi.domain.models.Irrf;
import com.gabrielmatheus.eniatusapi.domain.repositories.IrrfRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class IrrfService extends ServiceGeneric<Irrf> {

  @Autowired
  private IrrfRepository irrfRepository;

  @Override
  public JpaRepository<Irrf, Long> getRepository() {
    return irrfRepository;
  }

  @Override
  public Irrf update(Irrf irrf, Long id) {
    if(!getRepository().existsById(id)){
      return null;
    }

    irrf.setId(id);
    return getRepository().save(irrf);
  }

  
}
