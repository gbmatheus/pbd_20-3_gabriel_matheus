// package com.gabrielmatheus.eniatusapi.api.controllers;

// import java.math.BigDecimal;
// import java.util.List;
// import java.util.Optional;

// import javax.validation.Valid;

// import com.gabrielmatheus.eniatusapi.domain.models.Descontos;
// import com.gabrielmatheus.eniatusapi.domain.models.OutrosDescontos;
// import com.gabrielmatheus.eniatusapi.domain.repositories.DescontosRepository;
// import com.gabrielmatheus.eniatusapi.domain.repositories.OutrosDescontosRepository;
// import com.gabrielmatheus.eniatusapi.domain.services.descontos.AdicionarDescontosService;
// import com.gabrielmatheus.eniatusapi.domain.services.descontos.CadastrarDescontosService;
// import com.gabrielmatheus.eniatusapi.domain.services.descontos.CalcularDescontosService;
// import com.gabrielmatheus.eniatusapi.domain.services.descontos.DescontosService;

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
// @RequestMapping("/api/descontos")
// public class DescontosController {

//   private final DescontosRepository descontosRepository;
//   private final OutrosDescontosRepository outrosDescontosRepository;
//   private final DescontosService descontosService;
//   private final CadastrarDescontosService cadastrarDescontosService;
//   private final AdicionarDescontosService addOutrosDescontosServices;
//   private final CalcularDescontosService calcularDescontosService;

//   @Autowired
//   public DescontosController(DescontosRepository descontosRepository,OutrosDescontosRepository outrosDescontosRepository, DescontosService descontosService,
//     CadastrarDescontosService cadastrarDescontosService, AdicionarDescontosService addOutrosDescontosServices, CalcularDescontosService calcularDescontosService) {
//     this.descontosRepository = descontosRepository;
//     this.outrosDescontosRepository = outrosDescontosRepository;
//     this.descontosService = descontosService;
//     this.cadastrarDescontosService = cadastrarDescontosService;
//     this.addOutrosDescontosServices = addOutrosDescontosServices;
//     this.calcularDescontosService = calcularDescontosService;
//   }

//   /**
//   @GetMapping
//   public List<Descontos> index() {
//     return descontosRepository.findAll();
//   }

//   @PostMapping
//   @ResponseStatus(HttpStatus.CREATED)
//   public Descontos create(@Valid @RequestBody Descontos d) {
//     Descontos descontos = cadastrarDescontosService.createDescontos(d);
//     return descontos;
//   }

//   @GetMapping("/{id]")
//   public ResponseEntity<Descontos> show(@PathVariable Long id) {
//     Optional<Descontos> descontos = descontosRepository.findById(id);

//     if (!descontos.isPresent()) {
//       return ResponseEntity.notFound().build();
//     }

//     return ResponseEntity.ok().body(descontos.get());
//   }

//   @PutMapping("/{id}")
//   public ResponseEntity<Descontos> update(@PathVariable Long id, @Valid @RequestBody Descontos d) {
//     Descontos descontos = descontosService.update(d, id);

//     if (descontos == null)
//       return ResponseEntity.notFound().build();

//     return ResponseEntity.ok(descontos);
//   }

//   @DeleteMapping("/{id}")
//   public ResponseEntity<Void> delete(@PathVariable Long id) {
//     if (!descontosService.delete(id)) {
//       return ResponseEntity.notFound().build();
//     }

//     return ResponseEntity.noContent().build();
//   } 
//   */

//   /**
//    * 
//    */
//   @GetMapping("/{id}/outros-descontos")
//   public ResponseEntity<List<OutrosDescontos>> indexAcrescimos(@PathVariable Long id) {
//     Optional<Descontos> descontos = descontosRepository.findById(id);

//     if (!descontos.isPresent()) {
//       return ResponseEntity.notFound().build();
//     }

//     List<OutrosDescontos> descontosOutrosDescontos = outrosDescontosRepository.findByDescontos(descontos.get());

//     return ResponseEntity.ok(descontosOutrosDescontos);
//   }

//   @PostMapping("/{id}/outros-descontos/")
//   public ResponseEntity<OutrosDescontos> createOutrosDescontos(@PathVariable Long id,
//       @RequestBody OutrosDescontos outrosDescontos) {
//     OutrosDescontos oa = addOutrosDescontosServices.addDescontos(id, outrosDescontos);

//     if (oa == null) {
//       return ResponseEntity.badRequest().build();
//     }

//     return ResponseEntity.ok().body(oa);
//   }


//   // Será que é necessario
//   @GetMapping("/{id}/calculo")
//   public ResponseEntity<BigDecimal> calculateOutrosDescontos (@PathVariable Long id) {
//     Optional<Descontos> descontos = descontosRepository.findById(id);

//     if (!descontos.isPresent()) {
//       return ResponseEntity.notFound().build();
//     }

//     BigDecimal total = calcularDescontosService.calcularTotalOutrosDescontos(descontos.get());

//     return ResponseEntity.ok().body(total);
//   }


// }
