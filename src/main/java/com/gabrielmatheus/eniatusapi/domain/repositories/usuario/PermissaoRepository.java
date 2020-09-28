package com.gabrielmatheus.eniatusapi.domain.repositories.usuario;

import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.usuario.Permissao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

  Optional<Permissao> findByNome (String nome);
  
  // Optional<Usuario> findByUsuario(MeuUsuario ususario);
  // 
  // // findByGruposIn(List<Grupo> grupos)
  // List<Permisao> findByGroupIn(List<Permisao> grupos);
  
}
