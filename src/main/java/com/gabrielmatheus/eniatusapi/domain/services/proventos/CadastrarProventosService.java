package com.gabrielmatheus.eniatusapi.domain.services.proventos;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;

// import com.gabrielmatheus.eniatusapi.domain.exceptions.BusinessException;
// import com.gabrielmatheus.eniatusapi.domain.models.Funcionario;
// import com.gabrielmatheus.eniatusapi.domain.models.descartar.Proventos;
// import com.gabrielmatheus.eniatusapi.domain.repositories.FuncionarioRepository;

// import org.springframework.beans.factory.annotation.Autowired;

// @Service
public class CadastrarProventosService {

  // // @Autowired
  // // private ProventosRepository proventosRepository;
  
  // @Autowired
  // private FuncionarioRepository funcionarioRepository;
  
  // public Proventos createProventos (Long id, Proventos proventos) {
  //   Funcionario funcionario =  funcionarioRepository.findById(id)
  //   .orElseThrow(() -> new BusinessException("Funcionário não existe"));

  //   proventos.setAdicionalTempoServico(proventos.getAdicionalTempoServico() == null ? new BigDecimal(0) : proventos.getAdicionalTempoServico());
  //   proventos.setHoraExtra(proventos.getHoraExtra() == null ? 0 : proventos.getHoraExtra());
  //   proventos.setAuxAlimentacao(proventos.getAuxAlimentacao() == null ? new BigDecimal(0) : proventos.getAuxAlimentacao());
    
  //   proventos.setFuncionario(funcionario);
  //   proventos.setDataProvento(LocalDateTime.now());

  //   // return proventosRepository.save(proventos);
  //   return null;
  // }
  
}
