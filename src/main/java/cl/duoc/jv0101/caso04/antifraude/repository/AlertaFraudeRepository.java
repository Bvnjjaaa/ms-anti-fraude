package cl.duoc.jv0101.caso04.antifraude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.jv0101.caso04.antifraude.model.AlertaFraude;

public interface AlertaFraudeRepository extends JpaRepository<AlertaFraude, Long> {
}
