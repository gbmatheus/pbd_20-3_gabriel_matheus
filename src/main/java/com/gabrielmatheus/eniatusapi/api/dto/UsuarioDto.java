package com.gabrielmatheus.eniatusapi.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class UsuarioDto {

  @NotBlank
  private String login;

  @NotBlank
  private String senha;
  
}
