package com.gabrielmatheus.eniatusapi.domain.models.horas_extras;

import java.math.BigDecimal;

public interface HoraExtra {

  public BigDecimal aplicarHoraExtraSobre(Integer horasTrabalhadas, BigDecimal valorHora);
  
}
