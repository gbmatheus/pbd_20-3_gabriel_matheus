package com.gabrielmatheus.eniatusapi.domain.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoas")
public class Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(min=3, max = 50)
  private String nome;

  @NotBlank
  @Column(unique = true)
  @Size(min = 11, max = 14)
  private String cpf;

  @NotNull
  private LocalDateTime dt_nascimento;
  
  @Size(max = 40)
  private String naturalidade;

  private LocalDateTime dt_admissao;

}
