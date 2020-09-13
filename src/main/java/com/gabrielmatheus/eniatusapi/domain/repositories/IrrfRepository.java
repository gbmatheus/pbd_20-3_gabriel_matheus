package com.gabrielmatheus.eniatusapi.domain.repositories;

import com.gabrielmatheus.eniatusapi.domain.models.Irrf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrrfRepository extends JpaRepository<Irrf, Long>{

}
