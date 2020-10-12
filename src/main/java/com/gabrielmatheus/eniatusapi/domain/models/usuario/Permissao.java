package com.gabrielmatheus.eniatusapi.domain.models.usuario;

import javax.persistence.Entity;
// import javax.persistence.EnumType;
// import javax.persistence.Enumerated;
import javax.persistence.Id;

// import com.gabrielmatheus.eniatusapi.domain.models.enums.TipoPermissao;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Permissao implements GrantedAuthority {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  private String nome;

  @Override
  public String getAuthority() {
    return nome;
  }
  
}
