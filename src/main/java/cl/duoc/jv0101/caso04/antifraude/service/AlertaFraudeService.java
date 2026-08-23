package cl.duoc.jv0101.caso04.antifraude.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import cl.duoc.jv0101.caso04.antifraude.model.AlertaFraude;
import cl.duoc.jv0101.caso04.antifraude.repository.AlertaFraudeRepository;

@Service
public class AlertaFraudeService {

    private final AlertaFraudeRepository repository;

    public AlertaFraudeService(AlertaFraudeRepository repository) {
        this.repository = repository;
    }

    public List<AlertaFraude> findAll() {
        return repository.findAll();
    }

    public Optional<AlertaFraude> findById(Long id) {
        return repository.findById(id);
    }

    public AlertaFraude create(AlertaFraude recurso) {
        return repository.save(recurso);
    }

    public Optional<AlertaFraude> update(Long id, AlertaFraude datos) {
        return repository.findById(id).map(existente -> {
            existente.setNombre(datos.getNombre());
            existente.setOperacion(datos.getOperacion());
            existente.setNivel(datos.getNivel());
            return repository.save(existente);
        });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(existente -> {
            repository.delete(existente);
            return true;
        }).orElse(false);
    }
}
