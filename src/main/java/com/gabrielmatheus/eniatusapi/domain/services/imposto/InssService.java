package com.gabrielmatheus.eniatusapi.domain.services.imposto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Inss;
import com.gabrielmatheus.eniatusapi.domain.repositories.InssRepository;
import com.gabrielmatheus.eniatusapi.domain.services.ServiceGeneric;
import com.gabrielmatheus.eniatusapi.domain.utils.VerificarVigencia;

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

    Optional<Inss> inssAtual = inssRepository.findByAliquotaAndAtivo(inss.getAliquota(), true);

    inss.setVigencia(inss.getVigencia() == null ? LocalDateTime.now().getYear() : inss.getVigencia());
    inss.setMes(inss.getMes() == null ? LocalDateTime.now().getMonthValue() : inss.getMes());
    inss.setPeriodo(LocalDateTime.now());
    inss.setAtivo(true);

    // Ser for de meses anteriores
    if (inssAtual.isPresent()) {
      Boolean inssNovoEAtual = VerificarVigencia.atual(inss.getMes(), inss.getVigencia(), inssAtual.get().getMes(),
          inssAtual.get().getVigencia());

      inss.setAtivo(inssNovoEAtual);
      inssAtual.get().setAtivo(!inssNovoEAtual);
      getRepository().save(inssAtual.get());

    }

    return getRepository().save(inss);

  }

  @Override
  public Inss update(Inss inss, Long id) {
    if (!getRepository().existsById(id)) {
      return null;
    }

    inss.setId(id);
    return getRepository().save(inss);
  }

}
