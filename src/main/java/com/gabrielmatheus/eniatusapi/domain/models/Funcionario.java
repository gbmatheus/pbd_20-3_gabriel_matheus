package com.gabrielmatheus.eniatusapi.domain.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.gabrielmatheus.eniatusapi.domain.models.enums.TipoContratacao;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String cod_funcionario;

  @Enumerated(EnumType.STRING)
  private TipoContratacao tipo;

  @NotNull
  private Integer quantidade_filhos;

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getCod_funcionario() {
    return this.cod_funcionario;
  }

  public void setCod_funcionario(final String cod_funcionario) {
    this.cod_funcionario = cod_funcionario;
  }

  public TipoContratacao getTipo() {
    return this.tipo;
  }

  public void setTipo(final TipoContratacao tipo) {
    this.tipo = tipo;
  }

  public Integer getQuantidade_filhos() {
    return this.quantidade_filhos;
  }

  public void setQuantidade_filhos(final Integer quantidade_filhos) {
    this.quantidade_filhos = quantidade_filhos;
  }

}
