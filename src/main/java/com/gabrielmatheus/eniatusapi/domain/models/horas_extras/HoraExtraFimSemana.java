package com.gabrielmatheus.eniatusapi.domain.models.horas_extras;

import java.math.BigDecimal;

public class HoraExtraFimSemana implements HoraExtra {

  @Override
  public BigDecimal aplicarHoraExtraSobre(Integer horasTrabalhadas, BigDecimal valorHora) {
    return valorHora.multiply(new BigDecimal(horasTrabalhadas));
  }
  
}
