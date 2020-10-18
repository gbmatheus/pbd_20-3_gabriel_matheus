package com.gabrielmatheus.eniatusapi.domain.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Irrf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrrfRepository extends JpaRepository<Irrf, Long>{

  Optional<Irrf> findByAliquota(BigDecimal aliquota);

  List<Irrf> findByVigencia(Integer vigencia);

  List<Irrf> findByAtivo(Boolean ativo);

  Optional<Irrf> findByAliquotaAndAtivo(BigDecimal aliquota, Boolean ativo);

}
