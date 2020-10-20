package com.gabrielmatheus.eniatusapi.domain.services.proventos;

import java.time.LocalDateTime;

import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.FolhaMensal;
import com.gabrielmatheus.eniatusapi.domain.models.OutrosAcrescimos;
import com.gabrielmatheus.eniatusapi.domain.repositories.FolhaMensalRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.OutrosAcrescimosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarAcrescimosService {

  private final FolhaMensalRepository folhaMensalRepository;
  private final OutrosAcrescimosRepository outrosAcrescimosRepository;

  @Autowired
  public AdicionarAcrescimosService(FolhaMensalRepository folhaMensalRepository,
      OutrosAcrescimosRepository outrosAcrescimosRepository) {
    this.folhaMensalRepository = folhaMensalRepository;
    this.outrosAcrescimosRepository = outrosAcrescimosRepository;
  }

  public OutrosAcrescimos addAcrescimos(Long id, OutrosAcrescimos outrosAcrescimos) {

    FolhaMensal folhaMensal = folhaMensalRepository.findById(id)
        .orElseThrow(() -> new BusinessException("Folha de pagamento não encontrado"));

    outrosAcrescimos.setFolhaMensal(folhaMensal);
    outrosAcrescimos.setDataAcrecimo(LocalDateTime.now());

    return outrosAcrescimosRepository.save(outrosAcrescimos);
  }

}
