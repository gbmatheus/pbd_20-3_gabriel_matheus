package com.gabrielmatheus.eniatusapi.domain.repositories;

import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

  Optional<Pessoa> findByCpf(String cpf);
  
}
