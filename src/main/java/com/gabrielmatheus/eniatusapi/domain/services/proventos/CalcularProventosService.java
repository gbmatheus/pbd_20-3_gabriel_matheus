package com.gabrielmatheus.eniatusapi.domain.services.proventos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
import com.gabrielmatheus.eniatusapi.domain.models.FolhaMensal;
import com.gabrielmatheus.eniatusapi.domain.models.OutrosAcrescimos;
import com.gabrielmatheus.eniatusapi.domain.models.adicional_tempo.AdicionalPorTempo;
import com.gabrielmatheus.eniatusapi.domain.models.horas_extras.HoraExtra;
import com.gabrielmatheus.eniatusapi.domain.repositories.FolhaMensalRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.OutrosAcrescimosRepository;
import com.gabrielmatheus.eniatusapi.domain.utils.ContextoMatematico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalcularProventosService {

  private final OutrosAcrescimosRepository outrosAcrescimosRepository;
  private final FolhaMensalRepository folhaMensalRepository;


  @Autowired
  public CalcularProventosService (FolhaMensalRepository folhaMensalRepository, 
      OutrosAcrescimosRepository outrosAcrescimosRepository) {
        this.folhaMensalRepository = folhaMensalRepository;
    this.outrosAcrescimosRepository = outrosAcrescimosRepository;
  }

  public BigDecimal calcularTotalProventos (Long id, BigDecimal valorHora, /**Para teste */ HoraExtra tipoHoraExtra, AdicionalPorTempo tipoAdiconal) {
    BigDecimal salario = new BigDecimal(1045.00);
    BigDecimal totalProventos = new BigDecimal(0);

    FolhaMensal folhaMensal = folhaMensalRepository.findById(id)
      .orElseThrow(() -> new BusinessException("Nenhum provento existente"));

    totalProventos = totalProventos.add(tipoHoraExtra.aplicarHoraExtraSobre(folhaMensal.getHoraExtra(), valorHora), ContextoMatematico.arredondar());
    totalProventos = totalProventos.add(folhaMensal.getAuxAlimentacao(), ContextoMatematico.arredondar());
    totalProventos = totalProventos.add(tipoAdiconal.aplicarAdicionalPorTempo(salario), ContextoMatematico.arredondar());
    totalProventos = totalProventos.add(calcularTotalAcrescimo(folhaMensal), ContextoMatematico.arredondar());
    
    return totalProventos;

  }


  public BigDecimal calcularTotalAcrescimo (FolhaMensal folhaMensal) {

    BigDecimal totalAcrescimos = new BigDecimal(0);
    
    List<OutrosAcrescimos> outrosAcrescimos = outrosAcrescimosRepository.findByFolhaMensal(folhaMensal);

    if(!outrosAcrescimos.isEmpty()) {

      for (OutrosAcrescimos acrescimos : outrosAcrescimos) {
      
        if(acrescimos.getDataAcrecimo().getMonthValue() == LocalDateTime.now().getMonthValue()
        && acrescimos.getDataAcrecimo().getYear() == LocalDateTime.now().getYear()) {
     
          totalAcrescimos = totalAcrescimos.add(acrescimos.getValor());
        
        }

      }

      return totalAcrescimos;    

    }
    return totalAcrescimos;
  }
  
}
