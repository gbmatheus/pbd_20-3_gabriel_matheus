package com.gabrielmatheus.eniatusapi.domain.repositories;

import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Papeis;
import com.gabrielmatheus.eniatusapi.domain.models.enums.TipoPapeis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PapeisRepository extends JpaRepository<Papeis, Long> {
  Optional<Papeis> findByPapel(TipoPapeis tipoPapeis);
}
