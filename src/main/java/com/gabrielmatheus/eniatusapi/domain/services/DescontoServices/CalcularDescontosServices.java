package com.gabrielmatheus.eniatusapi.domain.services.DescontoServices;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.Inss;
import com.gabrielmatheus.eniatusapi.domain.models.Irrf;
import com.gabrielmatheus.eniatusapi.domain.repositories.InssRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.IrrfRepository;
import com.gabrielmatheus.eniatusapi.domain.utils.MC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalcularDescontosServices {

  private Integer anoAtual = LocalDateTime.now().getYear();

  @Autowired
  // private FuncionarioRepository funcionarioRepository;

  private final InssRepository inssRepository;
  private final IrrfRepository irrfRepository;

  /**
   * Descontos descontos 
   *  id
   *  contribuicaoSindical
   *  faltas
   *  totalDesconto
   *  inss
   *  irrf
   */


  public CalcularDescontosServices(InssRepository inssRepository, IrrfRepository irrfRepository) {
    this.inssRepository = inssRepository;
    this.irrfRepository = irrfRepository;
  }

  
  public Inss verificarInss() {
    // Teste criar variavel;
    BigDecimal salarioTeste = new BigDecimal(5000.00);

    List<Inss> inssAtual = inssRepository.findByVigencia(anoAtual);
    
    if(inssAtual.isEmpty()) {
      List<Inss> inssLista = inssRepository.findAll();

      /**
       * Adicionar excessao
       */
      if(!inssLista.isEmpty()) {
        System.out.println("vazio");
        throw new BusinessException("Nenhum inss cadastrado");
      }
      
      Integer indexUltimoElemento = inssLista.size() - 1;
      Integer ultimaVigencia = inssLista.get(indexUltimoElemento).getVigencia();

      inssAtual = inssRepository.findByVigencia(ultimaVigencia);

      for (Inss i : inssAtual) {
        /**
         * num.compareTo(num2)
         * Se for < retorna -1
         * Se for = retorna  0
         * Se for > retorna  1
         */
        if(salarioTeste.doubleValue() >= i.getValorMin().doubleValue()
          && salarioTeste.doubleValue() <= i.getValorMax().doubleValue()
        ){
          System.out.println(i.getId());
          salarioTeste = salarioTeste.multiply(i.getAliquota(), MC.mc());
          System.out.println(salarioTeste);
          salarioTeste = salarioTeste.divide(new BigDecimal(100), MC.mc());
          System.out.println(salarioTeste);
          // return i;
          return i;
        }
      }
      throw new BusinessException("Vázio");
    }

    for (Inss i : inssAtual) {
      /**
       * num.compareTo(num2)
       * Se for < retorna -1
       * Se for = retorna  0
       * Se for > retorna  1
       */
      if(salarioTeste.doubleValue() >= i.getValorMin().doubleValue()
        && salarioTeste.doubleValue() <= i.getValorMax().doubleValue()
      ){
        System.out.println(i.getId());
        salarioTeste = salarioTeste.multiply(i.getAliquota(), MC.mc());
        System.out.println(salarioTeste);
        salarioTeste = salarioTeste.divide(new BigDecimal(100), MC.mc());
        System.out.println(salarioTeste);
        return i;
      }
    }
    
    throw new BusinessException("Vázio");
  }

    
  public Inss verificarInssRecursiva(BigDecimal salario, Integer anoAtual) {
    // Teste criar variavel;
    // Adicionar exception quando a lista for vazia
    List<Inss> inssAtual = inssRepository.findByVigencia(anoAtual);
    if(inssRepository.findAll().isEmpty()) {
      /**
       * Adicionar exception
       */
      throw new BusinessException("Nenhum INSS cadastrado");
    }

    if(inssAtual.isEmpty()) {
      Integer anoAnterior = anoAtual - 1;
      verificarInssRecursiva(salario, anoAnterior);
    }

    for (Inss i : inssAtual) {
      /**
       * num.compareTo(num2)
       * Se for < retorna -1
       * Se for = retorna  0
       * Se for > retorna  1
       */
      if(salario.doubleValue() >= i.getValorMin().doubleValue()
        && salario.doubleValue() <= i.getValorMax().doubleValue()
      ){
        System.out.println(i.getId());
        salario = salario.multiply(i.getAliquota(), MC.mc());
        System.out.println(salario);
        salario = salario.divide(new BigDecimal(100), MC.mc());
        System.out.println(salario);
        return i;
      }
    }
    
    throw new BusinessException("Nenhum INSS corresponde ao salário");
  }

  public Irrf verificarIrrf() {
    // Teste criar variavel;
    BigDecimal salarioTeste = new BigDecimal(2045.00);

    List<Irrf> irrfAtual = irrfRepository.findByVigencia(anoAtual);
    
    if(irrfAtual.isEmpty()) {
      List<Irrf> irrfLista = irrfRepository.findAll();

      /**
       * Adicionar excessao
       */
      if(!irrfLista.isEmpty()) {
        System.out.println("vazio");
      }
      
      Integer indexUltimoElemento = irrfLista.size() - 1;
      Integer ultimaVigencia = irrfLista.get(indexUltimoElemento).getVigencia();

      irrfAtual = irrfRepository.findByVigencia(ultimaVigencia);

      for (Irrf i : irrfAtual) {
        /**
         * num.compareTo(num2)
         * Se for < retorna -1
         * Se for = retorna  0
         * Se for > retorna  1
         */
        if(salarioTeste.doubleValue() >= i.getBaseCalculoMin().doubleValue()
          && salarioTeste.doubleValue() <= i.getBaseCalculoMax().doubleValue()
        ){
          System.out.println(i.getId());
          System.out.println(i.getValorDeduzir());
          return i;
        }
      }
    }

    for (Irrf i : irrfAtual) {
      /**
       * num.compareTo(num2)
       * Se for < retorna -1
       * Se for = retorna  0
       * Se for > retorna  1
       */
      if(salarioTeste.doubleValue() >= i.getBaseCalculoMin().doubleValue()
      && salarioTeste.doubleValue() <= i.getBaseCalculoMax().doubleValue()
      ){
        System.out.println(i.getId());
        System.out.println(i.getValorDeduzir());
        return i;
      }
    }
    throw new BusinessException("Nenhum IRFF corresponde ao salário");
  }


  public Irrf verificarIrrfRecursiva(BigDecimal salario, Integer anoAtual) {
    // Teste criar variavel;
    if(irrfRepository.findAll().isEmpty()){
      // Adicionar uma exception
      throw new BusinessException("Nenhum salário cadastrado");
    }
    List<Irrf> irrfAtual = irrfRepository.findByVigencia(anoAtual);
    
    if(irrfAtual.isEmpty()) {
      System.out.println("vazio");
      Integer anoAnterior = anoAtual - 1;
      verificarIrrfRecursiva(salario, anoAnterior);
    }

    for (Irrf i : irrfAtual) {
      /**
       * num.compareTo(num2)
       * Se for < retorna -1
       * Se for = retorna  0
       * Se for > retorna  1
       */
      if(salario.doubleValue() >= i.getBaseCalculoMin().doubleValue()
        && salario.doubleValue() <= i.getBaseCalculoMax().doubleValue()
      ){
        /**
         * Adicionar o retorno e uma exceção para valores acima
         */
        System.out.println(i.getId());
        System.out.println(i.getValorDeduzir());
        return i;
      }
    }
    throw new BusinessException("Nenhum IRRF corresponde ao salário");
  }

  public void addOutrosDescontos() {
    
  }



}
