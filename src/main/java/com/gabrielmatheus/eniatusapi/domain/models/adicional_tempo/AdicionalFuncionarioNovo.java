package com.gabrielmatheus.eniatusapi.domain.models.adicional_tempo;

import java.math.BigDecimal;

public class AdicionalFuncionarioNovo implements AdicionalPorTempo {

  @Override
  public BigDecimal aplicarAdicionalPorTempo(BigDecimal salario) {
    return salario.multiply(new BigDecimal(0.04));
  }
  
}
