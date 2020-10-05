package com.gabrielmatheus.eniatusapi.domain.services.proventos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import org.springframework.stereotype.Service;

@Service
public class AdicionalPorTempoService {

  public BigDecimal calcularAddPorTempo (LocalDateTime adimissao, BigDecimal salario) {

    LocalDateTime contratado = LocalDateTime.of(2000, Month.APRIL, 30, 0, 0);

    if(adimissao.isBefore(contratado)) {
      return salario.multiply(new BigDecimal(0.04));
    }

    return salario.multiply(new BigDecimal(0.05));

  }
  
}
