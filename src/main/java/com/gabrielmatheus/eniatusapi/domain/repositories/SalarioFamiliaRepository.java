package com.gabrielmatheus.eniatusapi.domain.repositories;

import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.SalarioFamilia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalarioFamiliaRepository extends JpaRepository<SalarioFamilia, Long> {

  Optional<SalarioFamilia> findByVigencia(Integer vigencia);

  Optional<SalarioFamilia> findByAtivo(Boolean ativo);

}
