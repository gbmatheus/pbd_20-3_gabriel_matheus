package com.gabrielmatheus.eniatusapi.domain.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.gabrielmatheus.eniatusapi.domain.models.FolhaMensal;
import com.gabrielmatheus.eniatusapi.domain.models.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolhaMensalRepository extends JpaRepository<FolhaMensal, Long> {
  
  List<FolhaMensal> findByFuncionario(Funcionario funcionario);

  List<FolhaMensal> findByMes(Integer mes);

  List<FolhaMensal> findByAno(Integer ano);

  List<FolhaMensal> findByPeriodo(LocalDateTime periodo); 

}
