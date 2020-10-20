package com.gabrielmatheus.eniatusapi.domain.repositories;

import java.util.List;

import com.gabrielmatheus.eniatusapi.domain.models.FolhaMensal;
import com.gabrielmatheus.eniatusapi.domain.models.OutrosDescontos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutrosDescontosRepository extends JpaRepository<OutrosDescontos, Long> {
  
  List<OutrosDescontos> findByFolhaMensal(FolhaMensal folhaMensal);

}
