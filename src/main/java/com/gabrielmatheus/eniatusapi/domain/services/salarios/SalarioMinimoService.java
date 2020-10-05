package com.gabrielmatheus.eniatusapi.domain.services.salarios;

import java.time.LocalDateTime;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.SalarioMinimo;
import com.gabrielmatheus.eniatusapi.domain.repositories.SalarioMinimoRepository;
import com.gabrielmatheus.eniatusapi.domain.services.ServiceGeneric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SalarioMinimoService extends ServiceGeneric<SalarioMinimo> {

  @Autowired
  private SalarioMinimoRepository salarioMinimoRepository;

  @Override
  public JpaRepository<SalarioMinimo, Long> getRepository() {
    return salarioMinimoRepository;
  }

  @Override
  public SalarioMinimo save(SalarioMinimo salarioMinimo) {
    
    Optional<SalarioMinimo> salarioMinimoAtual = salarioMinimoRepository.findByAtivo(true);

    salarioMinimo.setVigencia(salarioMinimo.getVigencia() == null ? LocalDateTime.now().getYear() : salarioMinimo.getVigencia());
    salarioMinimo.setMes(salarioMinimo.getMes() == null ? LocalDateTime.now().getMonthValue() : salarioMinimo.getMes());
    salarioMinimo.setPeriodo(LocalDateTime.now());

    // Ser for de meses anteriores
    if(salarioMinimoAtual.isPresent() 
      && salarioMinimoAtual.get().getVigencia() <= salarioMinimo.getVigencia() 
      && salarioMinimoAtual.get().getMes() <= salarioMinimo.getMes()
    ) {
      salarioMinimoAtual.get().setAtivo(false);
      getRepository().save(salarioMinimoAtual.get());
    } else {
      salarioMinimo.setAtivo(true);
    }

    return getRepository().save(salarioMinimo);
  }

  @Override
  public SalarioMinimo update(SalarioMinimo salarioMinimo, Long id) {
    if (!getRepository().existsById(id)) {
      // throw new BusinessException("Nenhum salário mínimo encontrado");
      return null;
    }

    salarioMinimo.setId(id);
    return getRepository().save(salarioMinimo);
  }

  public SalarioMinimo isActive () {
    Optional<SalarioMinimo> salario = salarioMinimoRepository.findByAtivo(true);

    if(!salario.isPresent()) {
      throw new BusinessException("Nenhum salário minímo encontrado");
    }

    return salario.get();
  }

}
