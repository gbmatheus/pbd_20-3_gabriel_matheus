package com.gabrielmatheus.eniatusapi.domain.services.folha_mensal;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.FolhaMensal;
import com.gabrielmatheus.eniatusapi.domain.models.Funcionario;
import com.gabrielmatheus.eniatusapi.domain.models.Inss;
import com.gabrielmatheus.eniatusapi.domain.models.Irrf;
import com.gabrielmatheus.eniatusapi.domain.models.SalarioFamilia;
import com.gabrielmatheus.eniatusapi.domain.models.SalarioMinimo;
import com.gabrielmatheus.eniatusapi.domain.repositories.FolhaMensalRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.FuncionarioRepository;
import com.gabrielmatheus.eniatusapi.domain.services.imposto.ImpostoService;
import com.gabrielmatheus.eniatusapi.domain.services.proventos.AdicionalPorTempoService;
import com.gabrielmatheus.eniatusapi.domain.services.salarios.SalarioFamiliaService;
import com.gabrielmatheus.eniatusapi.domain.services.salarios.SalarioMinimoService;
import com.gabrielmatheus.eniatusapi.domain.utils.ConverteSalario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolhaDoFuncionarioService {

  private final FolhaMensalRepository folhaMensalRepository;
  private final FuncionarioRepository funcionarioRepository;
  private final ImpostoService impostoService;
  private final SalarioMinimoService salarioMinimoService;
  private final SalarioFamiliaService salarioFamiliaService;
  private final AdicionalPorTempoService adicionalPorTempoService;

  @Autowired
  public FolhaDoFuncionarioService(FolhaMensalRepository folhaMensalRepository, FuncionarioRepository funcionarioRepository, ImpostoService impostoService,
      SalarioMinimoService salarioMinimoService, SalarioFamiliaService salarioFamiliaService,
      AdicionalPorTempoService adicionalPorTempoService) {
    this.folhaMensalRepository = folhaMensalRepository;
    this.funcionarioRepository = funcionarioRepository;
    this.impostoService = impostoService;
    this.salarioMinimoService = salarioMinimoService;
    this.salarioFamiliaService = salarioFamiliaService;
    this.adicionalPorTempoService = adicionalPorTempoService;
  }

  // criar a folha de pagamento do funcionario daquele mes
  public FolhaMensal createFolha(Long funcionarioID, FolhaMensal folhaMensal) {
    LocalDateTime hoje = LocalDateTime.now();

    /**
     * Verificando se o funcionario existe
     */
    Funcionario funcionario = funcionarioRepository.findById(funcionarioID)
        .orElseThrow(() -> new BusinessException("Funcionário não encontrado"));

    /**
     * Convertendo valores para o salario
     */
    BigDecimal salario = ConverteSalario.converterParaBigDecimal(funcionario.getSalario());

    /**
     * Buscando os valores configurados
     */
    Inss inss = impostoService.verificarInss(salario, hoje.getYear());
    Irrf irrf = impostoService.verificarIrrf(salario, hoje.getYear());
    SalarioMinimo salarioMinimo = salarioMinimoService.isActive();
    SalarioFamilia salarioFamilia = salarioFamiliaService.isActive();

    /**
     * System.out.println("Settando valores inss, irrf e salario"); 
     */
    folhaMensal.setInss(inss);
    folhaMensal.setIrrf(irrf);
    folhaMensal.setSalarioMinimo(salarioMinimo);
    folhaMensal.setSalarioFamilia(salarioFamilia);
    
    /**
     * Verificando e calculando adicional por tempo
     System.out.println("Calculando e settando adicional por tempo");
     */
    folhaMensal.setAdicionalTempoServico(adicionalPorTempoService.calcularAddPorTempo(funcionario.getDt_admissao(),
        ConverteSalario.converterParaBigDecimal(funcionario.getSalario())));

    /**
     * Setando mes, ano e periodo. Se não existir mês e ano é setado o atual
     */
    folhaMensal.setMes(folhaMensal.getMes() == null ? hoje.getMonthValue() : folhaMensal.getMes());
    folhaMensal.setAno(folhaMensal.getAno() == null ? hoje.getYear() : folhaMensal.getAno());
    folhaMensal.setPeriodo(hoje);

    /**
     * Setando funcionario
     * Adicionadno funcionario na lista"
     */
    folhaMensal.getFuncionario().add(funcionario);


    
    return folhaMensalRepository.save(folhaMensal);
  }

}
