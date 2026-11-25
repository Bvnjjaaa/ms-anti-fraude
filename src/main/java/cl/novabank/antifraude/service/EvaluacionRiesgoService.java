package cl.novabank.antifraude.service;

import cl.novabank.antifraude.model.EvaluacionRiesgo;
import cl.novabank.antifraude.repository.EvaluacionRiesgoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluacionRiesgoService {

    private static final double UMBRAL_RIESGO_ALTO = 5_000_000d;
    private static final double UMBRAL_RIESGO_MEDIO = 1_000_000d;

    private final EvaluacionRiesgoRepository repository;

    public EvaluacionRiesgo evaluarTransaccion(EvaluacionRiesgo evaluacion) {
        evaluacion.setId(null);
        double monto = evaluacion.getMonto();

        if (monto > UMBRAL_RIESGO_ALTO) {
            evaluacion.setNivelRiesgo("ALTO");
            evaluacion.setAprobado(false);
            evaluacion.setMotivo("Transacción RECHAZADA: monto superior a $5.000.000, riesgo alto de fraude");
        } else if (monto > UMBRAL_RIESGO_MEDIO) {
            evaluacion.setNivelRiesgo("MEDIO");
            evaluacion.setAprobado(true);
            evaluacion.setMotivo("Transacción APROBADA con alerta: monto superior a $1.000.000, riesgo medio");
        } else {
            evaluacion.setNivelRiesgo("BAJO");
            evaluacion.setAprobado(true);
            evaluacion.setMotivo("Transacción APROBADA: monto dentro del rango permitido, riesgo bajo");
        }

        evaluacion.setFechaEvaluacion(LocalDateTime.now());
        return repository.save(evaluacion);
    }

    public EvaluacionRiesgo obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No se encontró una evaluación con id " + id));
    }

    public EvaluacionRiesgo obtenerPorTransaccion(Long transaccionId) {
        return repository.findFirstByTransaccionIdOrderByIdDesc(transaccionId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No se encontró una evaluación para la transacción " + transaccionId));
    }

    public List<EvaluacionRiesgo> listarTodas() {
        return repository.findAll();
    }

    public List<EvaluacionRiesgo> listarPorCliente(String rutCliente) {
        return repository.findByRutCliente(rutCliente);
    }

    public List<EvaluacionRiesgo> listarPorNivelRiesgo(String nivelRiesgo) {
        return repository.findByNivelRiesgo(nivelRiesgo);
    }

}
