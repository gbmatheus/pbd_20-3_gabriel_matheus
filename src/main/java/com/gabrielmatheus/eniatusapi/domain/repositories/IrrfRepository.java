package com.gabrielmatheus.eniatusapi.domain.repositories;

import java.util.List;

import com.gabrielmatheus.eniatusapi.domain.models.Irrf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrrfRepository extends JpaRepository<Irrf, Long>{

  List<Irrf> findByVigencia(Integer vigencia);

}
