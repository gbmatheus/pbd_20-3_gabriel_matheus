package com.gabrielmatheus.eniatusapi.domain.services.imposto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Irrf;
import com.gabrielmatheus.eniatusapi.domain.repositories.IrrfRepository;
import com.gabrielmatheus.eniatusapi.domain.services.ServiceGeneric;
import com.gabrielmatheus.eniatusapi.domain.utils.VerificarVigencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class IrrfService extends ServiceGeneric<Irrf> {

  @Autowired
  private IrrfRepository irrfRepository;

  @Override
  public JpaRepository<Irrf, Long> getRepository() {
    return irrfRepository;
  }

  @Override
  public Irrf save(Irrf irrf) {
    Optional<Irrf> irrfAtual = irrfRepository.findByAliquotaAndAtivo(irrf.getAliquota(), true);
    
    irrf.setVigencia(irrf.getVigencia() == null ? LocalDateTime.now().getYear() : irrf.getVigencia());
    irrf.setMes(irrf.getMes() == null ? LocalDateTime.now().getMonthValue() : irrf.getMes());
    irrf.setPeriodo(LocalDateTime.now());
    irrf.setAtivo(true);
    
    // Ser for de meses anteriores
    if(irrfAtual.isPresent()) {
      Boolean irrfNovoEAtual = VerificarVigencia.atual(irrf.getMes(), irrf.getVigencia(), irrfAtual.get().getMes(), irrfAtual.get().getVigencia());

      irrf.setAtivo(irrfNovoEAtual);
      irrfAtual.get().setAtivo(!irrfNovoEAtual);
      getRepository().save(irrfAtual.get());
      
    }
    

    return getRepository().save(irrf);

  }

  @Override
  public Irrf update(Irrf irrf, Long id) {
    if(!getRepository().existsById(id)){
      return null;
    }

    irrf.setId(id);
    return getRepository().save(irrf);
  }

  
}
