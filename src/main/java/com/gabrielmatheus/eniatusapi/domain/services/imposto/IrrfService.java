package com.gabrielmatheus.eniatusapi.domain.services.imposto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Irrf;
import com.gabrielmatheus.eniatusapi.domain.repositories.IrrfRepository;
import com.gabrielmatheus.eniatusapi.domain.services.ServiceGeneric;

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
    Optional<Irrf> irrfAtual = irrfRepository.findByAliquota(irrf.getAliquota());

    irrf.setVigencia(irrf.getVigencia() == null ? LocalDateTime.now().getYear() : irrf.getVigencia());
    irrf.setMes(irrf.getMes() == null ? LocalDateTime.now().getMonthValue() : irrf.getMes());
    
    // if (irrf.getMes() == null) {
    //   irrf.setMes(LocalDateTime.now().getMonthValue());
    // }
    // if (irrf.getVigencia() == null) {
    //   irrf.setVigencia(LocalDateTime.now().getYear());
    // }
    
    // Ser for de meses anteriores
    if(irrfAtual.isPresent() 
      && irrfAtual.get().getVigencia() <= irrf.getVigencia() 
      && irrfAtual.get().getMes() <= irrf.getMes()
    ) {
      irrfAtual.get().setAtivo(false);
      getRepository().save(irrfAtual.get());
    } else {
      irrf.setAtivo(true);
    }
    
    irrf.setPeriodo(LocalDateTime.now());

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
