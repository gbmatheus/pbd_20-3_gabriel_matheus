package com.gabrielmatheus.eniatusapi.domain.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Inss;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InssRepository extends JpaRepository<Inss, Long> {

  Optional<Inss> findByAliquota(BigDecimal aliquota);

  List<Inss> findByVigencia(Integer vigencia);
  
  List<Inss> findByAtivo(Boolean ativo);
  
}
