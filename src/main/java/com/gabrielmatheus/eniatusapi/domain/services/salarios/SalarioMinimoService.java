package com.gabrielmatheus.eniatusapi.domain.services.salarios;

import java.time.LocalDateTime;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.SalarioMinimo;
import com.gabrielmatheus.eniatusapi.domain.repositories.SalarioMinimoRepository;
import com.gabrielmatheus.eniatusapi.domain.services.ServiceGeneric;
import com.gabrielmatheus.eniatusapi.domain.utils.VerificarVigencia;

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

    salarioMinimo
        .setVigencia(salarioMinimo.getVigencia() == null ? LocalDateTime.now().getYear() : salarioMinimo.getVigencia());
    salarioMinimo.setMes(salarioMinimo.getMes() == null ? LocalDateTime.now().getMonthValue() : salarioMinimo.getMes());
    salarioMinimo.setPeriodo(LocalDateTime.now());
    salarioMinimo.setAtivo(true);

    // Ser for de meses anteriores
    if (salarioMinimoAtual.isPresent()) {
      Boolean eAtual = VerificarVigencia.atual(salarioMinimo.getMes(), salarioMinimo.getVigencia(),
          salarioMinimoAtual.get().getMes(), salarioMinimoAtual.get().getVigencia());

      salarioMinimoAtual.get().setAtivo(!eAtual);
      salarioMinimo.setAtivo(eAtual);

      getRepository().save(salarioMinimoAtual.get());
    }

    // Don't touch
    /**
     * if (salarioMinimoAtual.isPresent()) { if (salarioMinimo.getVigencia() >
     * salarioMinimoAtual.get().getVigencia()) { // 2020 >= 2020
     * salarioMinimoAtual.get().setAtivo(false); } else if
     * (salarioMinimo.getVigencia() == salarioMinimoAtual.get().getVigencia()){ if
     * (salarioMinimo.getMes() >= salarioMinimoAtual.get().getMes()) { // 11 >= 12
     * salarioMinimoAtual.get().setAtivo(false); } else {
     * salarioMinimo.setAtivo(false); } } else { salarioMinimo.setAtivo(false); }
     * getRepository().save(salarioMinimoAtual.get()); }
     */

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

  public SalarioMinimo isActive() {
    Optional<SalarioMinimo> salario = salarioMinimoRepository.findByAtivo(true);

    if (!salario.isPresent()) {
      throw new BusinessException("Nenhum salário minímo encontrado");
    }

    return salario.get();
  }

}
