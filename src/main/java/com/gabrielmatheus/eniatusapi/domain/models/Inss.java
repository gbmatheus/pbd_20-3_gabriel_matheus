package com.gabrielmatheus.eniatusapi.domain.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "inss")
public class Inss {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private Integer vigencia;
  
  @NotNull
  private BigDecimal valorMax;
  
  @NotNull
  private BigDecimal valorMin;

  @NotNull
  private BigDecimal aliquota;

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

  public BigDecimal getValorMax() {
    return this.valorMax;
  }

  public void setValorMax(BigDecimal valorMax) {
    this.valorMax = valorMax;
  }

  public BigDecimal getValorMin() {
    return this.valorMin;
  }

  public void setValorMin(BigDecimal valorMin) {
    this.valorMin = valorMin;
  }

  public BigDecimal getAliquota() {
    return this.aliquota;
  }

  public void setAliquota(BigDecimal aliquota) {
    this.aliquota = aliquota;
  }
  
}
