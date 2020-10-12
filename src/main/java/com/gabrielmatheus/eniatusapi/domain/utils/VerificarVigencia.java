package com.gabrielmatheus.eniatusapi.domain.utils;

public class VerificarVigencia {

  /**
   * Verificar se a vigencia é mais recente
   */
  public static Boolean atual(Integer mesElementoNovo, Integer anoElementoNovo, Integer mesElementoAntigo,
      Integer anoElementoAntigo) {
    // System.out.println(anoElementoNovo + " > " + anoElementoAntigo + " " + (anoElementoNovo > anoElementoAntigo));
    // System.out.println(anoElementoNovo + " < " + anoElementoAntigo + " " + (anoElementoNovo < anoElementoAntigo));
    // System.out.println(anoElementoNovo + " = " + anoElementoAntigo + " " + (anoElementoNovo == anoElementoAntigo));

    if (anoElementoNovo.intValue() > anoElementoAntigo.intValue()) {
      return true;

    } else if (anoElementoNovo.intValue() == anoElementoAntigo.intValue()) { // 2021 == 2021
      if (mesElementoNovo.intValue() >= mesElementoAntigo.intValue()) { // 11 >= 11
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }

  }
}
