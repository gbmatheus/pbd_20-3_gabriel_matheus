package com.gabrielmatheus.eniatusapi.domain.models;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class FolhaDePagamento {

  @Id
  private Long id;

  @ManyToOne
  @JoinColumn(name = "folha_mensal_id")
  private FolhaMensal folhaMensal;


  @ManyToOne
  @JoinColumn(name = "funcionario_id")
  private Funcionario funcionario;
  
  // private LocalDateTime data;

}
