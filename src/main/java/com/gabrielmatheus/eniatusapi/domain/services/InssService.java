package com.gabrielmatheus.eniatusapi.domain.services;

import com.gabrielmatheus.eniatusapi.domain.models.Inss;
import com.gabrielmatheus.eniatusapi.domain.repositories.InssRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class InssService extends ServiceGeneric<Inss> {

  @Autowired
  private InssRepository inssRepository;

  @Override
  public JpaRepository<Inss, Long> getRepository() {
    return inssRepository;
  }

  @Override
  public Inss update(Inss inss, Long id) {
    if(!getRepository().existsById(id)) {
      return null;
    }

    inss.setId(id);
    return getRepository().save(inss);
  }

}
