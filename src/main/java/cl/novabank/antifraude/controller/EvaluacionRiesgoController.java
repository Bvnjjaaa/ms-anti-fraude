package cl.novabank.antifraude.controller;

import cl.novabank.antifraude.model.EvaluacionRiesgo;
import cl.novabank.antifraude.service.EvaluacionRiesgoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/anti-fraude")
@RequiredArgsConstructor
public class EvaluacionRiesgoController {

    private final EvaluacionRiesgoService service;

    @PostMapping("/evaluar")
    public ResponseEntity<EvaluacionRiesgo> evaluar(@Valid @RequestBody EvaluacionRiesgo evaluacion) {
        EvaluacionRiesgo resultado = service.evaluarTransaccion(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<EvaluacionRiesgo>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionRiesgo> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping("/transaccion/{transaccionId}")
    public ResponseEntity<EvaluacionRiesgo> obtenerPorTransaccion(@PathVariable Long transaccionId) {
        return ResponseEntity.ok(service.obtenerPorTransaccion(transaccionId));
    }

    @GetMapping("/cliente/{rut}")
    public ResponseEntity<List<EvaluacionRiesgo>> listarPorCliente(@PathVariable String rut) {
        return ResponseEntity.ok(service.listarPorCliente(rut));
    }

}
