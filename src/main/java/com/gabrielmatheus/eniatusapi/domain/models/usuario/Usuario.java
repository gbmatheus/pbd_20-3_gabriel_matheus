package com.gabrielmatheus.eniatusapi.domain.models.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gabrielmatheus.eniatusapi.domain.models.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(unique = true)
  private String login;

  @Email
  @NotNull
  private String email;

  @Column(length = 100)
  @NotBlank
  @Size(min = 6)
  private String senha;

  @OneToOne
  private Pessoa pessoa;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Permissao> permissoes = new ArrayList<>();

  public Usuario (Usuario usuario) {
    // super();
    this.login = usuario.getLogin();
    this.email = usuario.getEmail();
    this.senha = usuario.getSenha();
    this.permissoes = usuario.getPermissoes();
    this.id = usuario.getId();
  }

}
