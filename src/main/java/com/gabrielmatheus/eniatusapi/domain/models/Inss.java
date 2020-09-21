package com.gabrielmatheus.eniatusapi.domain.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
  
}
