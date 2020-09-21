package com.gabrielmatheus.eniatusapi.domain.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Proventos {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // obrigatorio
  private BigDecimal salario;
  // se houver
  private BigDecimal adicionalTempoServico;
  // se houver
  private Integer horaExtra;
  // se houver
  private BigDecimal auxAlimentacao;

  private BigDecimal salarioBruto;

}
