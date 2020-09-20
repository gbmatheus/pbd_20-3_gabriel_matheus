package com.gabrielmatheus.eniatusapi.domain.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "salario_familia")
public class SalarioFamilia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private Integer vigencia;

  @NotNull
  private BigDecimal remuneracao;

  @NotNull
  private BigDecimal valorUnitario;

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

  public BigDecimal getRemuneracao() {
    return this.remuneracao;
  }

  public void setRemuneracao(BigDecimal remuneracao) {
    this.remuneracao = remuneracao;
  }

  public BigDecimal getValorUnitario() {
    return this.valorUnitario;
  }

  public void setValorUnitario(BigDecimal valorUnitario) {
    this.valorUnitario = valorUnitario;
  }
  
}
