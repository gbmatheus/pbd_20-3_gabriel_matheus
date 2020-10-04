package com.gabrielmatheus.eniatusapi.domain.models.descartar;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.gabrielmatheus.eniatusapi.domain.models.Funcionario;
import com.gabrielmatheus.eniatusapi.domain.models.Inss;
import com.gabrielmatheus.eniatusapi.domain.models.Irrf;
import com.gabrielmatheus.eniatusapi.domain.models.SalarioFamilia;
import com.gabrielmatheus.eniatusapi.domain.models.SalarioMinimo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FolhaDoMes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false)
  private Integer mes = LocalDateTime.now().getMonthValue();

  @Column(nullable = false)
  private Integer ano = LocalDateTime.now().getYear();

  @Column(nullable = false)
  private LocalDateTime periodo;

  @Column(nullable = false)
  @ManyToOne
  private Funcionario funcionario;

  @ManyToOne
  private Proventos proventos;

  @ManyToOne
  private Descontos descontos;

  @Column(nullable = false)
  @ManyToOne
  private Inss inss;

  @Column(nullable = false)
  @ManyToOne
  private Irrf irrf;

  @Column(nullable = false)
  @ManyToOne
  private SalarioMinimo salarioMinimo;

  @Column(nullable = false)
  @ManyToOne
  private SalarioFamilia salarioFamilia;
}
