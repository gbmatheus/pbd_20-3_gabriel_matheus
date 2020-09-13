package com.gabrielmatheus.eniatusapi.domain.services;

import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.SalarioFamilia;
import com.gabrielmatheus.eniatusapi.domain.repositories.SalarioFamiliaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SalarioFamiliaService extends ServiceGeneric<SalarioFamilia> {

  @Autowired
  private SalarioFamiliaRepository salarioFamiliaRepository;

  @Override
  public JpaRepository<SalarioFamilia, Long> getRepository() {
    return salarioFamiliaRepository;
  }

  @Override
  public SalarioFamilia update(SalarioFamilia sf, Long id) {
    Optional<SalarioFamilia> salarioFamilia = getRepository().findById(id);

    if(!salarioFamilia.isPresent()) {{
      return null;
    }}

    sf.setId(id);
    return getRepository().save(sf);
  }
  
}
