package com.gabrielmatheus.eniatusapi.domain.services.imposto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Inss;
import com.gabrielmatheus.eniatusapi.domain.repositories.InssRepository;
import com.gabrielmatheus.eniatusapi.domain.services.ServiceGeneric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class InssService extends ServiceGeneric<Inss> {

  @Autowired
  private InssRepository inssRepository;

  @Override
  public JpaRepository<Inss, Long> getRepository() {
    return inssRepository;
  }

  @Override
  public Inss save(Inss inss) {

    Optional<Inss> inssAtual = inssRepository.findByAliquota(inss.getAliquota());

    inss.setVigencia(inss.getVigencia() == null ? LocalDateTime.now().getYear() : inss.getVigencia());
    inss.setMes(inss.getMes() == null ? LocalDateTime.now().getMonthValue() : inss.getMes());
    
    // Ser for de meses anteriores
    if(inssAtual.isPresent() 
      && inssAtual.get().getVigencia() <= inss.getVigencia() 
      && inssAtual.get().getMes() <= inss.getMes()
    ) {
      inssAtual.get().setAtivo(false);
      getRepository().save(inssAtual.get());
    } else {
      inss.setAtivo(true);
    }
    
    inss.setPeriodo(LocalDateTime.now());

    return getRepository().save(inss);

  }

  @Override
  public Inss update(Inss inss, Long id) {
    if(!getRepository().existsById(id)) {
      return null;
    }

    inss.setId(id);
    return getRepository().save(inss);
  }

}
