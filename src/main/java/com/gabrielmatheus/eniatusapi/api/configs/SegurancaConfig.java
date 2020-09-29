package com.gabrielmatheus.eniatusapi.api.configs;

import com.gabrielmatheus.eniatusapi.domain.services.usuario.UsuarioLoginServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SegurancaConfig extends WebSecurityConfigurerAdapter{

  /**
   * Injeção de dependência do service responsável pelo login do usuario
   */
  @Autowired
  private UsuarioLoginServices userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // passwordencodes
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // passwordencodes
  }
  
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .httpBasic()
      .and()
      .authorizeRequests()
      .antMatchers("/api/usuarios/**").permitAll()// Só admin pode acessar
      .antMatchers("/api/funcionarios/**").hasAuthority("ADMIN") // Só admin pode acessar
      .anyRequest()
      .authenticated()
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  /** Teste de configurações anteriores
  // @Override
  // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  //   auth.inMemoryAuthentication()
  //     .withUser("admin").password("1234").roles("ADMIN")
  //     .and()
  //     .withUser("user").password("4321").roles("CONTADOR");
  // }
  // 
  // @Override
  // protected void configure(HttpSecurity http) throws Exception {
  //   http
  //     .authorizeRequests()
  //       .anyRequest().authenticated()
  //     .and()
  //     .httpBasic()
  //     .and()
  //     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  // }
  // 
  // Não é preciso de autenticação nas rotas
  // @Override
  // protected void configure(HttpSecurity http) throws Exception {
  //   http
  //     .httpBasic()
  //     .and()
  //     .authorizeRequests()
  //       .antMatchers().permitAll()
  //       .anyRequest().permitAll()
  //     .and()
  //     .csrf()
  //       .disable();
  // }
  */

  // @Bean
  public PasswordEncoder passwordEncoder() {
    // return NoOpPasswordEncoder.getInstance();
    // Utilizar cast do password encoder
    return new BCryptPasswordEncoder();
  }

  // @Bean
  // public PasswordEncoder passwordEncoder() {

  //   DelegatingPasswordEncoder delegatingPasswordEncoder = 
  //       (DelegatingPasswordEncoder) PasswordEncoderFactories
  //           .createDelegatingPasswordEncoder();

  //   delegatingPasswordEncoder
  //         .setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());

  //   return delegatingPasswordEncoder;
  // }
}
