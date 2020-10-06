package com.gabrielmatheus.eniatusapi.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OutrosDescontos {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String descricao;

  @NotNull
  private BigDecimal valor;

  @Column(nullable = false)
  private LocalDateTime dataDesconto;

  @ManyToOne
  @JoinColumn(name="folha_mensal_id")
  private FolhaMensal folhaMensal;
}
