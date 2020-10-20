package com.gabrielmatheus.eniatusapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SenhaDto {
  
  private String senha;
  private String novaSenha;

}
