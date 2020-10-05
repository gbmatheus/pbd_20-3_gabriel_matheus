package com.gabrielmatheus.eniatusapi.domain.services.folha_mensal;

import com.gabrielmatheus.eniatusapi.domain.models.FolhaMensal;
import com.gabrielmatheus.eniatusapi.domain.repositories.FolhaMensalRepository;
import com.gabrielmatheus.eniatusapi.domain.services.ServiceGeneric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FolhaMensalService extends ServiceGeneric<FolhaMensal> {

  @Autowired
  private FolhaMensalRepository folhaMensalRepository;

  @Override
  public JpaRepository<FolhaMensal, Long> getRepository() {
    return folhaMensalRepository;
  }

  @Override
  public FolhaMensal update(FolhaMensal folhaMensal, Long id) {
    if(!getRepository().existsById(id)) {
      return null;
    }

    folhaMensal.setId(id);
    folhaMensal = getRepository().save(folhaMensal);
    return folhaMensal;

  }
  
}
