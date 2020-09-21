package com.gabrielmatheus.eniatusapi.domain.repositories;

import java.util.List;

import com.gabrielmatheus.eniatusapi.domain.models.Inss;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InssRepository extends JpaRepository<Inss, Long> {

  List<Inss> findByVigencia(Integer vigencia);
  
}
