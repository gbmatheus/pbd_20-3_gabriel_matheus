package com.gabrielmatheus.eniatusapi.domain.repositories;

import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.SalarioMinimo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalarioMinimoRepository extends JpaRepository<SalarioMinimo, Long>{
  
  Optional<SalarioMinimo> findByVigencia(Integer vigencia);
  
  Optional<SalarioMinimo> findByAtivo(Boolean ativo);

}
