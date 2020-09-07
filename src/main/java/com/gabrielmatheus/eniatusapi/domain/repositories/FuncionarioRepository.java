package com.gabrielmatheus.eniatusapi.domain.repositories;

import com.gabrielmatheus.eniatusapi.domain.models.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
  
}
