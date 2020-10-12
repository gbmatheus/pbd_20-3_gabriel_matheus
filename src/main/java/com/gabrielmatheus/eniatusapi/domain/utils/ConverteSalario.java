package com.gabrielmatheus.eniatusapi.domain.utils;

import java.math.BigDecimal;

import com.gabrielmatheus.eniatusapi.domain.models.Salario;

public class ConverteSalario {

  public static BigDecimal converterParaBigDecimal (Salario salario) {
    BigDecimal salarioConvertido = new BigDecimal(salario.getHorasContratadas());

    salarioConvertido = salarioConvertido.multiply(salario.getValorDaHora(), ContextoMatematico.arredondar());
    salarioConvertido = salarioConvertido.multiply(salario.getQuantSemanas(), ContextoMatematico.arredondar());

    return salarioConvertido;
  }
  
}
