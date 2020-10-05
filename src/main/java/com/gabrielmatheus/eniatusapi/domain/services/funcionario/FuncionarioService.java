package com.gabrielmatheus.eniatusapi.domain.services.funcionario;

import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Funcionario;
import com.gabrielmatheus.eniatusapi.domain.repositories.FuncionarioRepository;
import com.gabrielmatheus.eniatusapi.domain.services.ServiceGeneric;

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

  public Boolean deactivate(Long id) {
    // Funcionario funcionario = funcionarioRepository.findById(id)
    //   .orElseThrow(() -> new BusinessException("Funcionário não encontrado"));

    Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

    if(!funcionario.isPresent()) {
      return false;
    }

    funcionario.get().setId(id);
    funcionario.get().setAtivo(!funcionario.get().getAtivo());
    getRepository().save(funcionario.get());
    return true;
  }
  
}
