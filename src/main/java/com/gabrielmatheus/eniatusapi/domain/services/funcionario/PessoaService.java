package com.gabrielmatheus.eniatusapi.domain.services.funcionario;


import com.gabrielmatheus.eniatusapi.domain.models.Pessoa;
import com.gabrielmatheus.eniatusapi.domain.repositories.PessoaRepository;
import com.gabrielmatheus.eniatusapi.domain.services.ServiceGeneric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService extends ServiceGeneric<Pessoa> {

  @Autowired
  private PessoaRepository pessoaRepository;

  @Override
  public JpaRepository<Pessoa, Long> getRepository() {
    return pessoaRepository;
  }

}
