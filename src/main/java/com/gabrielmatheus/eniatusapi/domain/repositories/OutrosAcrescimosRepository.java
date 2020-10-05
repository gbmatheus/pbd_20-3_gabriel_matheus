package com.gabrielmatheus.eniatusapi.domain.repositories;

import java.util.List;

import com.gabrielmatheus.eniatusapi.domain.models.FolhaMensal;
import com.gabrielmatheus.eniatusapi.domain.models.OutrosAcrescimos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutrosAcrescimosRepository extends JpaRepository<OutrosAcrescimos ,Long>{

  List<OutrosAcrescimos> findByFolhaMensal(FolhaMensal folhaMensal);
  
}
