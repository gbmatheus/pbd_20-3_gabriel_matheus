package com.gabrielmatheus.eniatusapi.domain.services.descontos;

import java.time.LocalDateTime;

import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.FolhaMensal;
import com.gabrielmatheus.eniatusapi.domain.models.OutrosDescontos;
import com.gabrielmatheus.eniatusapi.domain.repositories.FolhaMensalRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.OutrosDescontosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarDescontosService {

  private final FolhaMensalRepository folhaMensalRepository;
  private final OutrosDescontosRepository outrosDescontosRepository;

  @Autowired
  public AdicionarDescontosService(FolhaMensalRepository folhaMensalRepository,
      OutrosDescontosRepository outrosDescontosRepository) {
    this.folhaMensalRepository = folhaMensalRepository;
    this.outrosDescontosRepository = outrosDescontosRepository;
  }

  public OutrosDescontos addDescontos(Long id, OutrosDescontos outrosDescontos) {
    
    FolhaMensal folhaMensal = folhaMensalRepository.findById(id)
        .orElseThrow(() -> new BusinessException("Folha de pagamento não encontrado"));
    
    outrosDescontos.setFolhaMensal(folhaMensal);
    outrosDescontos.setDataDesconto(LocalDateTime.now());

    return outrosDescontosRepository.save(outrosDescontos);
  }
  
}
