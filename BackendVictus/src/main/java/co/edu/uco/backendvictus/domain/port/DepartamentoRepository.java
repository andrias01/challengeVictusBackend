package co.edu.uco.backendvictus.domain.port;

import java.util.UUID;

import co.edu.uco.backendvictus.domain.model.Departamento;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartamentoRepository {

    Mono<Departamento> save(Departamento departamento);

    Mono<Departamento> findById(UUID id);

    Flux<Departamento> findAll();

    Mono<Void> deleteById(UUID id);
}
