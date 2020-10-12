package com.gabrielmatheus.eniatusapi.domain.services.usuario;

// import java.util.Optional;

// import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
// import com.gabrielmatheus.eniatusapi.domain.models.enums.TipoPermissao;
// import com.gabrielmatheus.eniatusapi.domain.models.usuario.Usuario;
// import com.gabrielmatheus.eniatusapi.domain.repositories.usuario.UsuarioRepository;
// import com.gabrielmatheus.eniatusapi.domain.models.usuario.Permissao;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuario {

  // @Autowired
  // private UsuarioRepository repository;

  // public MeuUsuario save(MeuUsuario usuario) {
  //   Optional<MeuUsuario> u = repository.findByLogin(usuario.getLogin());

  //   if(u.isPresent()) {
  //     throw new BusinessException("Usuário já existente");
  //   }

  //   String senha = passwordEncoder().encode(usuario.getSenha());
  //   System.out.println(senha);
  //   usuario.setSenha(senha);

  //   usuario.getPermissoes().add(new Permisao(TipoPermissao.ADMIN.name()));

  //   return repository.save(usuario);
  // }

  // public PasswordEncoder passwordEncoder() {
  //   return new BCryptPasswordEncoder();
  // }
  

  
}
