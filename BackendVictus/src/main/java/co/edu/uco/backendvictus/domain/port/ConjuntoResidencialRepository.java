package co.edu.uco.backendvictus.domain.port;

import java.util.UUID;

import co.edu.uco.backendvictus.domain.model.ConjuntoResidencial;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConjuntoResidencialRepository {

    Mono<ConjuntoResidencial> save(ConjuntoResidencial conjuntoResidencial);

    Mono<ConjuntoResidencial> findById(UUID id);

    Flux<ConjuntoResidencial> findAll();

    Mono<Void> deleteById(UUID id);
}
