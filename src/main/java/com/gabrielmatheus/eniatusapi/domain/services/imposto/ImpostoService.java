package com.gabrielmatheus.eniatusapi.domain.services.imposto;

import java.math.BigDecimal;
import java.util.List;

import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.Inss;
import com.gabrielmatheus.eniatusapi.domain.models.Irrf;
import com.gabrielmatheus.eniatusapi.domain.repositories.InssRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.IrrfRepository;
import com.gabrielmatheus.eniatusapi.domain.utils.ContextoMatematico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpostoService {

  private final IrrfRepository irrfRepository;
  private final InssRepository inssRepository;

  @Autowired
  public ImpostoService(IrrfRepository irrfRepository, InssRepository inssRepository) {
    this.irrfRepository = irrfRepository;
    this.inssRepository = inssRepository;
  }

  public BigDecimal totalImposto(Inss inss, Irrf irrf, BigDecimal salario) {

    BigDecimal totalImposto = new BigDecimal(0);

    BigDecimal inssImposto = calcularInss(inss.getAliquota(), salario);

    totalImposto = totalImposto.add(irrf.getValorDeduzir());
    totalImposto = totalImposto.add(inssImposto);

    return totalImposto;
  }

  /**
   * Verifica qual irrf o salario se encaixa
   * 
   * @param salario
   * @param anoAtual
   * @return irrf
   * @return retorna o irrf daquela salario
   */
  public Irrf verificarIrrf(BigDecimal salario, Integer anoAtual) {

    List<Irrf> irrfAtual = irrfRepository.findByVigencia(anoAtual);

    if (irrfRepository.findAll().isEmpty()) {
      throw new BusinessException("Nenhum IRRF cadastrado");
    }

    if (irrfAtual.isEmpty()) {
      System.out.println("vazio");
      Integer anoAnterior = anoAtual - 1;
      verificarIrrf(salario, anoAnterior);
    }

    for (Irrf irrf : irrfAtual) {
      /**
       * num.compareTo(num2) Se for < retorna -1 Se for = retorna 0 Se for > retorna 1
       */
      if (salario.doubleValue() >= irrf.getBaseCalculoMin().doubleValue()
          && salario.doubleValue() <= irrf.getBaseCalculoMax().doubleValue()
          && irrf.getAtivo() == true
      ) {
        /**
         * Adicionar o retorno e uma exceção para valores acima
         */
        return irrf;

      } else if (salario.doubleValue() > irrf.getBaseCalculoMax().doubleValue()
        && irrf.getAtivo() == true
      ) {
        return irrf;
      }
    }
    throw new BusinessException("Nenhum IRRF corresponde ao salário");
  }

  /**
   * Verifica em qual inss o salario se encaixa
   * 
   * @param salario
   * @param anoAtual
   * @return inss
   * @return retorna o inss para aquele salario
   */
  public Inss verificarInss(BigDecimal salario, Integer anoAtual) {
    // Teste criar variavel;
    // Adicionar exception quando a lista for vazia
    List<Inss> inssAtual = inssRepository.findByVigencia(anoAtual);

    if (inssRepository.findAll().isEmpty()) {
      throw new BusinessException("Nenhum INSS cadastrado");
    }

    if (inssAtual.isEmpty()) {
      Integer anoAnterior = anoAtual - 1;
      verificarInss(salario, anoAnterior);
    }

    for (Inss inss : inssAtual) {
      /**
       * num.compareTo(num2) Se for < retorna -1 Se for = retorna 0 Se for > retorna 1
       */
      if (salario.doubleValue() >= inss.getValorMin().doubleValue()
        && salario.doubleValue() <= inss.getValorMax().doubleValue()
        && inss.getAtivo() == true
      ) {
        return inss;

      } else if (salario.doubleValue() > inss.getValorMax().doubleValue()
        && inss.getAtivo() == true
      ) {
        return inss;
      }
    }

    throw new BusinessException("Nenhum INSS corresponde ao salário");
  }

  public BigDecimal calcularInss(BigDecimal aliquota, BigDecimal salario) {
    salario = salario.multiply(aliquota, ContextoMatematico.arredondar());
    salario = salario.divide(new BigDecimal(100), ContextoMatematico.arredondar());
    return salario;
  }

}
