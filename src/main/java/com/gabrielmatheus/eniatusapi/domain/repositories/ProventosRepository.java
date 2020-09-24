package com.gabrielmatheus.eniatusapi.domain.repositories;

import com.gabrielmatheus.eniatusapi.domain.models.Proventos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProventosRepository extends JpaRepository<Proventos, Long>{
  
}
