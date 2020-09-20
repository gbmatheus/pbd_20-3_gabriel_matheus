package com.gabrielmatheus.eniatusapi.api.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.gabrielmatheus.eniatusapi.api.exceptions.ResponseException.Campo;
import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Object> handlerBusiness (BusinessException ex, WebRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    ResponseException response = new ResponseException();
    response.setStatus(status.value());
    response.setTitulo(ex.getMessage());
    response.setDataHora(LocalDateTime.now());

    return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    
    ArrayList<Campo> campos = new ArrayList<Campo>();

    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      String nome = ((FieldError) error).getField();
      String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

      campos.add(new Campo(nome, mensagem));
    }

    ResponseException resposta = new ResponseException();
    
    resposta.setStatus(status.value());
    resposta.setTitulo("Uma ou mais campos estão inválidos.");
    resposta.setDataHora(LocalDateTime.now());
    resposta.setCampos(campos);

    return super.handleExceptionInternal(ex, resposta, headers, status, request);
  }
}
