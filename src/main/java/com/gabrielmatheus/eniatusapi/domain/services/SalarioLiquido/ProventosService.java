package com.gabrielmatheus.eniatusapi.domain.services.SalarioLiquido;

import com.gabrielmatheus.eniatusapi.domain.models.Proventos;
import com.gabrielmatheus.eniatusapi.domain.repositories.ProventosRepository;
import com.gabrielmatheus.eniatusapi.domain.services.ServiceGeneric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProventosService extends ServiceGeneric<Proventos> {

  @Autowired
  private ProventosRepository proventosRepository;

  @Override
  public JpaRepository<Proventos, Long> getRepository() {
    return proventosRepository;
  }

  @Override
  public Proventos update(Proventos proventos, Long id) {
    if(getRepository().existsById(id)) {
      return null;
    }

    proventos.setId(id);
    return getRepository().save(proventos);
    
  }  
}
