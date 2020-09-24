package com.gabrielmatheus.eniatusapi.domain.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "descontos")
public class Descontos {
  // inss, irrf, contribuicao sindical, faltas, outros_descontos, total_descontos

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // se houver
  private BigDecimal contribuicaoSindical;

  // se houver
  private Integer faltas;

  private BigDecimal totalDesconto;
  
  @OneToOne
  private Inss inss;
  
  @OneToOne
  private Irrf irrf;
  
}
