package com.gabrielmatheus.eniatusapi.domain.services;

import com.gabrielmatheus.eniatusapi.domain.models.SalarioMinimo;
import com.gabrielmatheus.eniatusapi.domain.repositories.SalarioMinimoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SalarioMinimoService extends ServiceGeneric<SalarioMinimo> {

  @Autowired
  private SalarioMinimoRepository salarioMinimoRepository;

  @Override
  public JpaRepository<SalarioMinimo, Long> getRepository() {
    return salarioMinimoRepository;
  }

  @Override
  public SalarioMinimo update(SalarioMinimo sm, Long id) {
    if(!getRepository().existsById(id)) {
      return null;
    }

    sm.setId(id);
    return getRepository().save(sm);
  }
  
}
