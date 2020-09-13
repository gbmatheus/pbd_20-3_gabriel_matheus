package com.gabrielmatheus.eniatusapi.domain.repositories;

import com.gabrielmatheus.eniatusapi.domain.models.OutrosAcrescimos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutrosAcrescimosRepository extends JpaRepository<OutrosAcrescimos ,Long>{
  
}
