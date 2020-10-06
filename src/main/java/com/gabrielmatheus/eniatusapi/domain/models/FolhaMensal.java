package com.gabrielmatheus.eniatusapi.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FolhaMensal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false)
  private Integer mes = LocalDateTime.now().getMonthValue();

  @Column(nullable = false)
  private Integer ano = LocalDateTime.now().getYear();

  @Column(nullable = false)
  private LocalDateTime periodo;

  @Column(columnDefinition = "decimal(10,2) default '0.00'")
  private BigDecimal adicionalTempoServico;
  
  @Column(columnDefinition = "integer default 0")
  private Integer horaExtra;

  @Column(columnDefinition = "decimal(10,2) default '0.00'")
  private BigDecimal auxAlimentacao;

  @Column(columnDefinition = "decimal(10,2) default '0.00'")
  private BigDecimal contribuicaoSindical;

  @Column(columnDefinition = "integer default 0")
  private Integer faltas;

  @ManyToOne
  private Inss inss;

  @ManyToOne
  private Irrf irrf;

  @ManyToOne
  private SalarioMinimo SalarioMinimo;

  @ManyToOne
  private SalarioFamilia salarioFamilia;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Funcionario> funcionario = new ArrayList<>();

}
