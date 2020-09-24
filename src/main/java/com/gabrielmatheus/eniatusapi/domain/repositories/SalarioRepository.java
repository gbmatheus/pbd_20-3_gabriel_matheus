package com.gabrielmatheus.eniatusapi.domain.repositories;

import com.gabrielmatheus.eniatusapi.domain.models.Salario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalarioRepository extends JpaRepository<Salario, Long> {
    
}
