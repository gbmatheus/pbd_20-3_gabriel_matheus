// package com.gabrielmatheus.eniatusapi.api.controllers;

// import java.math.BigDecimal;
// import java.util.List;
// import java.util.Optional;

// import javax.validation.Valid;

// import com.gabrielmatheus.eniatusapi.domain.models.OutrosAcrescimos;
// import com.gabrielmatheus.eniatusapi.domain.models.Proventos;
// import com.gabrielmatheus.eniatusapi.domain.repositories.OutrosAcrescimosRepository;
// import com.gabrielmatheus.eniatusapi.domain.repositories.ProventosRepository;
// import com.gabrielmatheus.eniatusapi.domain.services.proventos.AdicionarAcrescimos;
// import com.gabrielmatheus.eniatusapi.domain.services.proventos.CadastrarProventosService;
// import com.gabrielmatheus.eniatusapi.domain.services.proventos.ProventosService;
// import com.gabrielmatheus.eniatusapi.domain.services.proventos.CalcularProventosService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api/proventos")
// public class ProventosController {

//   private final ProventosRepository proventosRepository;
//   private final OutrosAcrescimosRepository outrosAcrescimosRepository;
//   private final ProventosService proventosService;
//   private final CadastrarProventosService cadastrarProventosService;
//   private final AdicionarAcrescimos acrescimosServices;
//   private final CalcularProventosService calcularProventosService;

//   @Autowired
//   public ProventosController(ProventosRepository proventosRepository, OutrosAcrescimosRepository outrosAcrescimosRepository, ProventosService proventosService,
//       CadastrarProventosService cadastrarProventosService, AdicionarAcrescimos acrescimosServices, CalcularProventosService calcularProventosService) {
//     this.proventosRepository = proventosRepository;
//     this.outrosAcrescimosRepository = outrosAcrescimosRepository;
//     this.proventosService = proventosService;
//     this.cadastrarProventosService = cadastrarProventosService;
//     this.acrescimosServices = acrescimosServices;
//     this.calcularProventosService = calcularProventosService;
//   }

//   @GetMapping
//   public List<Proventos> index() {
//     return proventosRepository.findAll();
//   }

//   @PostMapping
//   @ResponseStatus(HttpStatus.CREATED)
//   public Proventos create(@Valid @RequestBody Proventos p) {
//     Proventos proventos = cadastrarProventosService.createProventos(p);

//     return proventos;
//   }

//   @GetMapping("/{id}")
//   public ResponseEntity<Proventos> show(@PathVariable Long id) {
//     Optional<Proventos> proventos = proventosRepository.findById(id);

//     if (!proventos.isPresent()) {
//       return ResponseEntity.notFound().build();
//     }

//     return ResponseEntity.ok(proventos.get());
//   }

//   @PutMapping("/{id}")
//   public ResponseEntity<Proventos> update(@PathVariable Long id, @Valid @RequestBody Proventos p) {
//     Proventos proventos = proventosService.update(p, id);

//     if (proventos == null)
//       return ResponseEntity.notFound().build();

//     return ResponseEntity.ok(proventos);
//   }

//   @DeleteMapping("/{id}")
//   public ResponseEntity<Void> delete(@PathVariable Long id) {
//     if (!proventosService.delete(id)) {
//       return ResponseEntity.notFound().build();
//     }

//     return ResponseEntity.noContent().build();
//   }
  
//   /**
//    * 
//    */
//   @GetMapping("/{id}/outros-acrescimos")
//   public ResponseEntity<List<OutrosAcrescimos>> indexAcrescimos(@PathVariable Long id) {
//     Optional<Proventos> proventos = proventosRepository.findById(id);
    
//     if (!proventos.isPresent()) {
//       return ResponseEntity.notFound().build();
//     }
    
//     List<OutrosAcrescimos> proventoAcrescimos = outrosAcrescimosRepository.findByProventos(proventos.get());

//     return ResponseEntity.ok(proventoAcrescimos);
//   }


//   @PostMapping("/{id}/outros-acrescimos")
//   @ResponseStatus(HttpStatus.CREATED)
//   public OutrosAcrescimos createAcrescimos(@PathVariable Long id,
//       @Valid @RequestBody OutrosAcrescimos outrosAcrescimos) {
//     return acrescimosServices.addAcrescimos(id, outrosAcrescimos);
//   }


//   // Será que é necessario
//   @GetMapping("/{id}/calculo")
//   public ResponseEntity<BigDecimal> calculateOutrosAcrescimos (@PathVariable Long id) {
//     Optional<Proventos> proventos = proventosRepository.findById(id);

//     if (!proventos.isPresent()) {
//       return ResponseEntity.notFound().build();
//     }

//     BigDecimal total = calcularProventosService.calcularTotalAcrescimo(proventos.get());

//     return ResponseEntity.ok().body(total);
//   }

//   // @GetMapping("/{id}/calculo-f")
//   // public ResponseEntity<BigDecimal> proventosFuncionario (@PathVariable Long
//   // id) {
//   // Optional<Proventos> proventos = proventosRepository.findById(id);

//   // if (!proventos.isPresent()) {
//   // return ResponseEntity.notFound().build();
//   // }

//   // BigDecimal total =
//   // calcularProventosService.calcularTotalProventos(proventos.get().getId(),
//   // new BigDecimal("5"), new HoraExtraDiaSemana(), new
//   // AdicionalFuncionarioAntigo());

//   // return ResponseEntity.ok().body(total);
//   // }

// }
