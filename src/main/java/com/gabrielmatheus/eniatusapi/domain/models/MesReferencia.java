package com.gabrielmatheus.eniatusapi.domain.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mes_de_referencia")
public class MesReferencia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Transient
  private LocalDateTime localDateTime = LocalDateTime.now();
  
  @NotNull
  private Integer mes = localDateTime.getMonthValue();

  // vigencia
  @NotNull
  private Integer ano = localDateTime.getYear();

  @ManyToOne
  private Funcionario funcionario;

  @ManyToOne
  private Proventos proventos;

  @ManyToOne
  private Descontos descontos;

  @ManyToOne
  private SalarioFamilia salarioFamilia;
}
