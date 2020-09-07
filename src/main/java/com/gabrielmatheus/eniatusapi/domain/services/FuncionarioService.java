package com.gabrielmatheus.eniatusapi.domain.services;

import com.gabrielmatheus.eniatusapi.domain.models.Funcionario;
import com.gabrielmatheus.eniatusapi.domain.repositories.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService extends ServiceGeneric<Funcionario> {

  @Autowired
  private FuncionarioRepository funcionarioRepository;

  @Override
  public JpaRepository<Funcionario, Long> getRepository() {
    return funcionarioRepository;
  }

  @Override
  public Funcionario update(Funcionario funcionario, Long id) {
    if(!getRepository().existsById(id)) {
      return null;
    }

    funcionario.setId(id);
    funcionario = getRepository().save(funcionario);
    return funcionario;

  }
  
}
