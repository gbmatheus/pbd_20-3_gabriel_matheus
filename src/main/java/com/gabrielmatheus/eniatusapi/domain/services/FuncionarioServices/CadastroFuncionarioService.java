package com.gabrielmatheus.eniatusapi.domain.services.FuncionarioServices;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.Funcionario;
import com.gabrielmatheus.eniatusapi.domain.models.Pessoa;
import com.gabrielmatheus.eniatusapi.domain.models.Salario;
import com.gabrielmatheus.eniatusapi.domain.models.SalarioMinimo;
import com.gabrielmatheus.eniatusapi.domain.models.enums.TipoContratacao;
import com.gabrielmatheus.eniatusapi.domain.repositories.FuncionarioRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.PessoaRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.SalarioMinimoRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.SalarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroFuncionarioService {

  // funcionario
  // dados da pessoa
  // dadis do salario
  @Autowired
  private PessoaRepository pessoaRepository;
  @Autowired
  private FuncionarioRepository funcionarioRepository;
  @Autowired
  private SalarioRepository salarioRepository;
  @Autowired
  private SalarioMinimoRepository salarioMinimoRepository;

  // Injeção de dependencia via contrutor
  // private final Repository repositorio;
  // public ClassePrincipal (Repository repositorio) {
  //   this.repositorio = repositorio;
  // }

  public Funcionario create(Funcionario funcionario) {
    Pessoa pessoa = createPessoa(funcionario.getPessoa());

    // Optional<Funcionario> f = funcionarioRepository.findByCod_funcionario(pessoa.getCpf());
    Optional<Funcionario> f = funcionarioRepository.findByPessoa(pessoa);

    if(f.isPresent()) {
      /**
       * Adicionar uma exception
       */
      System.out.println("Funcionario já existe");
      throw new BusinessException("Funcionário já existe");
    }

    Salario salario = createSalario(funcionario.getTipo(), funcionario.getSalario());
    if(salario == null) {
      /**
       * Adicionar uma exception
       */
      System.out.println("Salario minimo não existe");
      throw new BusinessException("Nenhum salário mínimo cadastrado");
    }
    
    funcionario.setPessoa(pessoa);
    funcionario.setSalario(salario);
    funcionario.setCod_funcionario(pessoa.getCpf());

    return funcionarioRepository.save(funcionario);
  }

  public Pessoa createPessoa (Pessoa pessoa) {
    // verificar se a pessoa existe, e o cpf dela
    Optional<Pessoa> p = pessoaRepository.findByCpf(pessoa.getCpf());
    
    if(p.isPresent()) {
      return p.get();
    }

    return pessoaRepository.save(pessoa);

  }

  public Salario createSalario (TipoContratacao tipo, Salario salario) {
    BigDecimal quantSemanas = new BigDecimal(4.5);
    
    // Se for horista
    if (tipo == TipoContratacao.HORA) {
      salario.setQuantSemanas(quantSemanas);
      return salarioRepository.save(salario);
    }
    
    // Se for mensalista (define a quantidades de horas padrão e o salario minimo)
    Integer anoAtual = LocalDateTime.now().getYear();
    Integer horas = 40;
    Optional<SalarioMinimo> salarioMinimo = salarioMinimoRepository
    .findByVigencia(anoAtual);

    if(!salarioMinimo.isPresent()) {
      Optional<SalarioMinimo> salarioMinimoAnterior = salarioMinimoRepository
        .findByVigencia(anoAtual - 1);
      
      if(!salarioMinimoAnterior.isPresent()){
        /**
         * Adicionar uma exception
         */
        System.out.println("Salario minimo não existe");
        throw new BusinessException("Salário minimo não existe");
      }

      salario.setQuantSemanas(quantSemanas);
      salario.setValorDaHora(
        converterSalario(salarioMinimoAnterior.get().getValor(), quantSemanas, horas)
      );

      return salarioRepository.save(salario);
    }    

    salario.setQuantSemanas(quantSemanas);
    salario.setValorDaHora(
      converterSalario(salarioMinimo.get().getValor(), quantSemanas, salario.getHorasContratadas())
    );

    return salarioRepository.save(salario);
  }

  /**
   * Converte o salario 
   * @param salarioMinimo
   * @param semanas
   * @param horas
   * @return
   */
  public BigDecimal converterSalario (BigDecimal salarioMinimo,
    BigDecimal semanas, Integer horas
  ) {

    BigDecimal salario = salarioMinimo.divide(semanas, new MathContext(3, RoundingMode.HALF_UP));
    salario = salario.divide(new BigDecimal(horas), new MathContext(3, RoundingMode.HALF_UP));

    System.out.println(salario);
    return salario;

  }
  
}
