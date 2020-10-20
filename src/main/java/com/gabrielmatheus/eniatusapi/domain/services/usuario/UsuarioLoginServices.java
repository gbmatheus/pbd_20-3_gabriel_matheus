package com.gabrielmatheus.eniatusapi.domain.services.usuario;

import java.util.Collection;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.usuario.Usuario;
import com.gabrielmatheus.eniatusapi.domain.repositories.usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioLoginServices implements UserDetailsService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    
    Optional<Usuario> usuario = usuarioRepository.findByLogin(login);

    if (!usuario.isPresent()) {
      throw new UsernameNotFoundException("Usuário não existe");
    }
    // System.out.println("Usuario " + usuario.get().getLogin());
    // System.out.println("Usuario " + usuario.get().getSenha());

    // if(!new Crypt().matches(usuario.get().getSenha(), ))

    
    // verificar se password é igual
    return new UsuarioSistema(usuario.get());
  }

  private final static class UsuarioSistema extends Usuario implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UsuarioSistema(Usuario usuario) {
    super(usuario);
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return getPermissoes();
    }

    @Override
    public String getPassword() {
      return super.getSenha();
    }

    @Override
    public String getUsername() {
      return this.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }

  }

}
