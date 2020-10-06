package com.gabrielmatheus.eniatusapi.domain.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gabrielmatheus.eniatusapi.domain.models.enums.TipoContratacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "funcionarios")
public class Funcionario {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(min = 11,  max = 14)
  private String cod_funcionario;
  
  @Column(columnDefinition = "integer default 0")
  private Integer quantidade_filhos;

  @Enumerated(EnumType.STRING)
  @Column(length = 5)
  private TipoContratacao tipo;
  
  @Column(nullable = false)
  private LocalDateTime dt_admissao;
  
  @NotNull
  @OneToOne
  private Pessoa pessoa;
  
  @NotNull
  @OneToOne(cascade = CascadeType.DETACH)
  private Salario salario;

  private Boolean ativo = true;
  
}
