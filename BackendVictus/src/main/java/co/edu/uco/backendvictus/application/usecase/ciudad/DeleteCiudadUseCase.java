package co.edu.uco.backendvictus.application.usecase.ciudad;

import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.backendvictus.crosscutting.exception.ApplicationException;
import co.edu.uco.backendvictus.domain.port.CiudadRepository;
import reactor.core.publisher.Mono;

@Service
public class DeleteCiudadUseCase {

    private final CiudadRepository ciudadRepository;

    public DeleteCiudadUseCase(final CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public Mono<Void> execute(final UUID id) {
        return ciudadRepository.findById(id)
                .switchIfEmpty(Mono.error(new ApplicationException("Ciudad no encontrada")))
                .flatMap(ciudad -> ciudadRepository.deleteById(id));
    }
}
