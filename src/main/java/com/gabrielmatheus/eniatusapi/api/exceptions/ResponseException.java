package com.gabrielmatheus.eniatusapi.api.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(Include.NON_NULL)
public class ResponseException {

  private Integer status;
  private LocalDateTime dataHora;
  private String titulo;
  private List<Campo> campos;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Campo {
    private String nome;
    private String mensagem;
  }
  
}
