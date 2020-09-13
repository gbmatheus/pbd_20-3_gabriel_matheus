package com.gabrielmatheus.eniatusapi.domain.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pessoas")
public class Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(min=3, max = 50)
  private String nome;

  @NotBlank
  @Size(min = 11, max = 14)
  private String cpf;

  private LocalDateTime dt_nascimento;
  
  @Size(max = 40)
  private String naturalidade;

  private LocalDateTime dt_admissao;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public LocalDateTime getDt_nascimento() {
    return this.dt_nascimento;
  }

  public void setDt_nascimento(LocalDateTime dt_nascimento) {
    this.dt_nascimento = dt_nascimento;
  }

  public String getNaturalidade() {
    return this.naturalidade;
  }

  public void setNaturalidade(String naturalidade) {
    this.naturalidade = naturalidade;
  }

  public LocalDateTime getDt_admissao() {
    return this.dt_admissao;
  }

  public void setDt_admissao(LocalDateTime dt_admissao) {
    this.dt_admissao = dt_admissao;
  }

}
