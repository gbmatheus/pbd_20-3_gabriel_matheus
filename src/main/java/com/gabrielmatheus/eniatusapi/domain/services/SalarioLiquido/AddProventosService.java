package com.gabrielmatheus.eniatusapi.domain.services.SalarioLiquido;

import java.math.BigDecimal;
import java.util.Optional;

import com.gabrielmatheus.eniatusapi.domain.models.Funcionario;
import com.gabrielmatheus.eniatusapi.domain.models.OutrosAcrescimos;
import com.gabrielmatheus.eniatusapi.domain.models.Proventos;
import com.gabrielmatheus.eniatusapi.domain.repositories.FuncionarioRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.OutrosAcrescimosRepository;
import com.gabrielmatheus.eniatusapi.domain.repositories.ProventosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddProventosService {

  @Autowired
  private FuncionarioRepository funcionarioRepository;

  @Autowired
  private ProventosRepository proventosRepository;

  @Autowired
  private OutrosAcrescimosRepository outrosAcrescimosRepository;

  public OutrosAcrescimos addOutrosAcrescimos(Long proventosID, OutrosAcrescimos outrosAcrescimos) {

    Optional<Proventos> proventos = proventosRepository.findById(proventosID);

    if (!proventos.isPresent()) {
      return null;
    }

    outrosAcrescimos.setProventos(proventos.get());

    return outrosAcrescimosRepository.save(outrosAcrescimos);
  }

  public BigDecimal calcularSalarioBruto(Long proventosID, Long funcionarioID) {
    // BigDecimal salarioBruto = new BigDecimal(0);
    Optional<Proventos> proventos = proventosRepository.findById(proventosID);
    Optional<Funcionario> funcionario = funcionarioRepository.findById(funcionarioID);


    if (!proventos.isPresent() || !funcionario.isPresent()) {
      return null;
    }
    

    return null;
  }


  // Calcular vencimento base se for horista
  // Calcular adicional por tempo
  // Calcular o hora extra
  // Calcular soma dos outros proventos

}
