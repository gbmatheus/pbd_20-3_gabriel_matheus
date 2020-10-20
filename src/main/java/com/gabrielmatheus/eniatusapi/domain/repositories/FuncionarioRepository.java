package com.gabrielmatheus.eniatusapi.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Funcionario;
import com.gabrielmatheus.eniatusapi.domain.models.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
  
  Optional<Funcionario> findByPessoa(Pessoa pessoa);

  List<Funcionario> findByAtivo(Boolean ativo);
  
}
