package co.edu.uco.backendvictus.application.usecase.pais;

import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.backendvictus.crosscutting.exception.ApplicationException;
import co.edu.uco.backendvictus.domain.port.PaisRepository;
import reactor.core.publisher.Mono;

@Service
public class DeletePaisUseCase {

    private final PaisRepository repository;

    public DeletePaisUseCase(final PaisRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> execute(final UUID id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ApplicationException("Pais no encontrado")))
                .flatMap(pais -> repository.deleteById(id));
    }
}
