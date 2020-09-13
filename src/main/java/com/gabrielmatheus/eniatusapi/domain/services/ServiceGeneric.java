package com.gabrielmatheus.eniatusapi.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public abstract class ServiceGeneric<T> {

  public abstract JpaRepository<T, Long> getRepository();

  public List<T> findAll() {
    return getRepository().findAll();
  }

  @Transactional
  public T save( T objeto) {
    return getRepository().save(objeto);
  }

  public Optional<T> findById( Long id) {
    return getRepository().findById(id);
  }

  @Transactional
  public T update(T t, Long id) {
    if(!getRepository().existsById(id)){
      return null;
    }
    return getRepository().save(t);
  }

  @Transactional
  public Boolean delete( Long id) {
    if(!getRepository().existsById(id))
      return false;
    
    getRepository().deleteById(id);
    return true;
  }

}
