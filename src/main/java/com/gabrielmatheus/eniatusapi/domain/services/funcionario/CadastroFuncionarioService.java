package com.gabrielmatheus.eniatusapi.domain.services.funcionario;

import java.math.BigDecimal;
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
import com.gabrielmatheus.eniatusapi.domain.utils.ContextoMatematico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroFuncionarioService {

  // funcionario
  // dados da pessoa
  // dadis do salario
  private final PessoaRepository pessoaRepository;
  private final FuncionarioRepository funcionarioRepository;
  private final SalarioRepository salarioRepository;
  private final SalarioMinimoRepository salarioMinimoRepository;

  // Injeção de dependencia via contrutor
  // private final Repository repositorio;
  // @Autowired
  // public ClassePrincipal (Repository repositorio) {
  // this.repositorio = repositorio;
  // }
  @Autowired
  public CadastroFuncionarioService(PessoaRepository pessoaRepository, FuncionarioRepository funcionarioRepository,
      SalarioRepository salarioRepository, SalarioMinimoRepository salarioMinimoRepository) {
    this.pessoaRepository = pessoaRepository;
    this.funcionarioRepository = funcionarioRepository;
    this.salarioRepository = salarioRepository;
    this.salarioMinimoRepository = salarioMinimoRepository;
  }

  public Funcionario create(Funcionario funcionario) {
    Pessoa pessoa = createPessoa(funcionario.getPessoa());

    // Optional<Funcionario> f =
    // funcionarioRepository.findByCod_funcionario(pessoa.getCpf());
    Optional<Funcionario> f = funcionarioRepository.findByPessoa(pessoa);

    if (f.isPresent()) {
      throw new BusinessException("Funcionário já existe");
    }

    Salario salario = createSalario(funcionario.getTipo(), funcionario.getSalario());

    if (salario == null) {
      throw new BusinessException("Nenhum salário mínimo cadastrado");
    }

    funcionario.setPessoa(pessoa);
    funcionario.setSalario(salario);
    funcionario.setCod_funcionario(pessoa.getCpf());
    funcionario.setDt_admissao(LocalDateTime.now());

    return funcionarioRepository.save(funcionario);
  }

  public Pessoa createPessoa(Pessoa pessoa) {
    // verificar se a pessoa existe, e o cpf dela
    Optional<Pessoa> p = pessoaRepository.findByCpf(pessoa.getCpf());

    if (p.isPresent()) {
      return p.get();
    }

    return pessoaRepository.save(pessoa);

  }

  public Salario createSalario(TipoContratacao tipo, Salario salario) {
    BigDecimal quantSemanas = new BigDecimal(5.5);

    // Se for horista
    if (tipo == TipoContratacao.HORA) {
      salario.setQuantSemanas(quantSemanas);
      return salarioRepository.save(salario);
    }

    // Se for mensalista (define a quantidades de horas padrão e o salario minimo)
    Integer anoAtual = LocalDateTime.now().getYear();
    Integer horas = 40;

    // Optional<SalarioMinimo> salarioMinimo = salarioMinimoRepository
    // .findByVigencia(anoAtual);

    Optional<SalarioMinimo> salarioMinimo = salarioMinimoRepository.findByAtivo(true);

    if (!salarioMinimo.isPresent()) {
      Optional<SalarioMinimo> salarioMinimoAnterior = salarioMinimoRepository.findByVigencia(anoAtual - 1);

      if (!salarioMinimoAnterior.isPresent()) {
        throw new BusinessException("Salário minimo não existe");
      }

      salario.setHorasContratadas(horas);
      salario.setQuantSemanas(quantSemanas);
      salario.setValorDaHora(converterSalario(salarioMinimoAnterior.get().getValor(), quantSemanas, horas));

      return salarioRepository.save(salario);
    }

    salario.setHorasContratadas(horas);
    salario.setQuantSemanas(quantSemanas);
    salario
        .setValorDaHora(converterSalario(salarioMinimo.get().getValor(), quantSemanas, salario.getHorasContratadas()));

    return salarioRepository.save(salario);
  }

  /**
   * Converte o salario
   * 
   * @param salarioMinimo
   * @param semanas
   * @param horas
   * @return
   */
  public BigDecimal converterSalario(BigDecimal salarioMinimo, BigDecimal semanas, Integer horas) {

    BigDecimal salario = salarioMinimo.divide(semanas, ContextoMatematico.arredondar());
    salario = salario.divide(new BigDecimal(horas), ContextoMatematico.arredondar());

    return salario;

  }

}
