package cl.novabank.antifraude.repository;

import cl.novabank.antifraude.model.EvaluacionRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluacionRiesgoRepository extends JpaRepository<EvaluacionRiesgo, Long> {

    List<EvaluacionRiesgo> findByRutCliente(String rutCliente);

    Optional<EvaluacionRiesgo> findFirstByTransaccionIdOrderByIdDesc(Long transaccionId);

    List<EvaluacionRiesgo> findByNivelRiesgo(String nivelRiesgo);

}
