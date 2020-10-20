package com.gabrielmatheus.eniatusapi.domain.services.salarios;

import java.time.LocalDateTime;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.SalarioFamilia;
import com.gabrielmatheus.eniatusapi.domain.repositories.SalarioFamiliaRepository;
import com.gabrielmatheus.eniatusapi.domain.services.ServiceGeneric;
import com.gabrielmatheus.eniatusapi.domain.utils.VerificarVigencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SalarioFamiliaService extends ServiceGeneric<SalarioFamilia> {

  @Autowired
  private SalarioFamiliaRepository salarioFamiliaRepository;

  @Override
  public JpaRepository<SalarioFamilia, Long> getRepository() {
    return salarioFamiliaRepository;
  }

  @Override
  public SalarioFamilia save(SalarioFamilia salarioFamilia) {

    Optional<SalarioFamilia> salarioFamiliaAtual = salarioFamiliaRepository.findByAtivo(true);

    salarioFamilia.setVigencia(salarioFamilia.getVigencia() == null ? LocalDateTime.now().getYear() : salarioFamilia.getVigencia());
    salarioFamilia.setMes(salarioFamilia.getMes() == null ? LocalDateTime.now().getMonthValue() : salarioFamilia.getMes());
    salarioFamilia.setPeriodo(LocalDateTime.now());
    salarioFamilia.setAtivo(true);
    
    if (salarioFamiliaAtual.isPresent()) {
      Boolean eAtual = VerificarVigencia.atual(salarioFamilia.getMes(), salarioFamilia.getVigencia(),
          salarioFamiliaAtual.get().getMes(), salarioFamiliaAtual.get().getVigencia());

      salarioFamilia.setAtivo(eAtual);
      salarioFamiliaAtual.get().setAtivo(!eAtual);
      getRepository().save(salarioFamiliaAtual.get());
    }
  
    return getRepository().save(salarioFamilia);
  }


  @Override
  public SalarioFamilia update(SalarioFamilia sf, Long id) {

    Optional<SalarioFamilia> salarioFamilia = getRepository().findById(id);

    if(!salarioFamilia.isPresent()) {{
      return null;
    }}

    sf.setId(id);
    return getRepository().save(sf);
  }


  public SalarioFamilia isActive () {
    Optional<SalarioFamilia> salario = salarioFamiliaRepository.findByAtivo(true);

    if(!salario.isPresent()) {
      throw new BusinessException("Nenhum salário minímo encontrado");
    }

    return salario.get();
  }
  
}
