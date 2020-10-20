package com.gabrielmatheus.eniatusapi.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inss {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Integer mes;

  @Column(nullable = false)
  private Integer vigencia;
  
  @NotNull
  @Column(scale = 2)
  private BigDecimal valorMax;
  
  @NotNull
  @Column(scale = 2)
  private BigDecimal valorMin;

  @NotNull
  @Column(scale = 2)
  private BigDecimal aliquota;

  @Column(nullable = false)
  private LocalDateTime periodo;

  @Column(nullable = false)
  private Boolean ativo;
  
}
