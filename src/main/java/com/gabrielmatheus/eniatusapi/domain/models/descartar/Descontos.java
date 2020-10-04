package com.gabrielmatheus.eniatusapi.domain.models.descartar;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.gabrielmatheus.eniatusapi.domain.models.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @Entity
public class Descontos {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "decimal(10,2) default '0.00'")
  private BigDecimal contribuicaoSindical;

  @Column(columnDefinition = "integer default 0")
  private Integer faltas;

  @Column(nullable = false)
  private LocalDateTime dataDesconto;

  @Column(nullable = false)
  @ManyToOne
  private Funcionario funcionario;

}
