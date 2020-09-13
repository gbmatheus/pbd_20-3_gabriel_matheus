package com.gabrielmatheus.eniatusapi.domain.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "irrf")
public class Irrf {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private Integer vigencia;
  
  @NotNull
  private BigDecimal baseCalculo;

  @NotNull
  private BigDecimal aliqota;

  @NotNull
  private BigDecimal valorDeduzir;
  
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public Integer getVigencia() {
    return this.vigencia;
  }

  public void setVigencia(Integer vigencia) {
    this.vigencia = vigencia;
  }

  public BigDecimal getBaseCalculo() {
    return this.baseCalculo;
  }

  public void setBaseCalculo(BigDecimal baseCalculo) {
    this.baseCalculo = baseCalculo;
  }

  public BigDecimal getAliqota() {
    return this.aliqota;
  }

  public void setAliqota(BigDecimal aliqota) {
    this.aliqota = aliqota;
  }

  public BigDecimal getValorDeduzir() {
    return this.valorDeduzir;
  }

  public void setValorDeduzir(BigDecimal valorDeduzir) {
    this.valorDeduzir = valorDeduzir;
  }
  
}
