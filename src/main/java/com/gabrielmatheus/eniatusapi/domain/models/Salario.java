package com.gabrielmatheus.eniatusapi.domain.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Salario {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private Integer horasContratadas;
  
  @NotNull
  @Column(scale = 2)
  private BigDecimal valorDaHora;
  
  @Column(columnDefinition = "decimal(10,1) default '5.5'")
  private BigDecimal quantSemanas;

  // @ManyToOne
  // private Funcionario funcionario;
  
}
