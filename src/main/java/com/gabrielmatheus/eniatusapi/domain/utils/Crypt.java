package com.gabrielmatheus.eniatusapi.domain.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class Crypt implements PasswordEncoder {

  @Override
  public String encode(CharSequence rawPassword) {
    return new BCryptPasswordEncoder().encode(rawPassword);
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
  }
  
}
