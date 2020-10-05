package com.gabrielmatheus.eniatusapi.domain.utils;

import java.math.MathContext;
import java.math.RoundingMode;

/**
 * MathContext
 */
public class ContextoMatematico {

  public static MathContext arredondar() {
    return new MathContext(3, RoundingMode.HALF_EVEN);
  }
  
}
