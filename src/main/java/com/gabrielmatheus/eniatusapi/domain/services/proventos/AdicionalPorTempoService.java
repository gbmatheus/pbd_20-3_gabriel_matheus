package com.gabrielmatheus.eniatusapi.domain.services.proventos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import com.gabrielmatheus.eniatusapi.domain.utils.ContextoMatematico;

import org.springframework.stereotype.Service;

@Service
public class AdicionalPorTempoService {

  public BigDecimal calcularAddPorTempo (LocalDateTime adimissao, BigDecimal salario) {

    LocalDateTime contratado = LocalDateTime.of(2000, Month.APRIL, 30, 0, 0);

    if(adimissao.isBefore(contratado)) {
      return salario.multiply(new BigDecimal(0.04), ContextoMatematico.arredondar());
    }

    return salario.multiply(new BigDecimal(0.05), ContextoMatematico.arredondar());

  }
  
}
