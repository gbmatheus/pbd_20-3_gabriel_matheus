package com.gabrielmatheus.eniatusapi.domain.models.horas_extras;

import java.math.BigDecimal;

public class HoraExtraDiaSemana implements HoraExtra {
  
  @Override
  public BigDecimal aplicarHoraExtraSobre(Integer horasTrabalhadas, BigDecimal valorHora) {
    return valorHora.multiply(new BigDecimal(0.5)).multiply(new BigDecimal(horasTrabalhadas));
  }

}
