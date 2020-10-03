package com.gabrielmatheus.eniatusapi.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
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

  @Column(columnDefinition = "decimal(10,2) default '0.00'")
  private BigDecimal adicionalTempoServico;
  
  @Column(columnDefinition = "integer default 0")
  private Integer horaExtra;

  @Column(columnDefinition = "decimal(10,2) default '0.00'")
  private BigDecimal auxAlimentacao;

  @Column(nullable = false)
  private LocalDateTime dataProvento;

}
