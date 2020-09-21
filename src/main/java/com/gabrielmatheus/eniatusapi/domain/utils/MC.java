package com.gabrielmatheus.eniatusapi.domain.utils;

import java.math.MathContext;
import java.math.RoundingMode;

/**
 * MathContext
 */
public class MC {

  public static MathContext mc() {
    return new MathContext(3, RoundingMode.HALF_UP);
  }
  
}
