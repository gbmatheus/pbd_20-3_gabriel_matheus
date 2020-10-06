package com.gabrielmatheus.eniatusapi.domain.services.descontos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.FolhaMensal;
import com.gabrielmatheus.eniatusapi.domain.models.OutrosDescontos;
import com.gabrielmatheus.eniatusapi.domain.repositories.FolhaMensalRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.OutrosDescontosRepository;
import com.gabrielmatheus.eniatusapi.domain.utils.ContextoMatematico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalcularDescontosService {

  private final OutrosDescontosRepository outrosDescontosRepository;
  private final FolhaMensalRepository folhaMensalRepository;

  @Autowired
  public CalcularDescontosService(FolhaMensalRepository folhaMensalRepository,
      OutrosDescontosRepository outrosDescontosRepository) {
    this.folhaMensalRepository = folhaMensalRepository;
    this.outrosDescontosRepository = outrosDescontosRepository;
  }

  /**
   * BigDecimal contribuicaoSindical BigDecimal faltas
   */
  public BigDecimal calcularTotalDescontos(Long id) {

    BigDecimal totalDescontos = new BigDecimal(0);

    FolhaMensal folhaMensal= folhaMensalRepository.findById(id)
        .orElseThrow(() -> new BusinessException("Nenhum desconto existente"));

    totalDescontos = totalDescontos.add(folhaMensal.getContribuicaoSindical(), ContextoMatematico.arredondar());
    // 
    // Errado
    totalDescontos = totalDescontos.add(folhaMensal.getContribuicaoSindical(), ContextoMatematico.arredondar());
    totalDescontos = totalDescontos.add(calcularTotalOutrosDescontos(folhaMensal), ContextoMatematico.arredondar());

    return totalDescontos;

  }

  public BigDecimal calcularTotalOutrosDescontos(FolhaMensal folhaMensal) {
    BigDecimal totalOutrosDescontos = new BigDecimal(0);

    List<OutrosDescontos> outrosDescontos = outrosDescontosRepository.findByFolhaMensal(folhaMensal);

    if (!outrosDescontos.isEmpty()) {

      for (OutrosDescontos decrescimos : outrosDescontos) {
        if (decrescimos.getDataDesconto().getMonthValue() == LocalDateTime.now().getMonthValue()
            && decrescimos.getDataDesconto().getYear() == LocalDateTime.now().getYear()) {
          
          System.out.println("Valor do descoto " + decrescimos.getValor());

          totalOutrosDescontos = totalOutrosDescontos.add(decrescimos.getValor());
          System.out.println(totalOutrosDescontos);
        }
      }

      return totalOutrosDescontos;
    }

    // throw new BusinessException("Nenhum acrescimo encontrado");
    return totalOutrosDescontos;
  }

}
