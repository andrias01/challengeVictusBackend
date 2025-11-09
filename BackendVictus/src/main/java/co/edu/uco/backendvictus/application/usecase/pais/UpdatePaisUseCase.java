package co.edu.uco.backendvictus.application.usecase.pais;

import co.edu.uco.backendvictus.application.usecase.UseCase;
import org.springframework.stereotype.Service;

import co.edu.uco.backendvictus.application.dto.pais.PaisResponse;
import co.edu.uco.backendvictus.application.dto.pais.PaisUpdateRequest;
import co.edu.uco.backendvictus.application.mapper.PaisApplicationMapper;
import co.edu.uco.backendvictus.crosscutting.exception.ApplicationException;
import co.edu.uco.backendvictus.domain.model.Pais;
import co.edu.uco.backendvictus.domain.port.PaisRepository;
import reactor.core.publisher.Mono;

@Service
public class UpdatePaisUseCase implements UseCase<PaisUpdateRequest, PaisResponse> {

    private final PaisRepository repository;
    private final PaisApplicationMapper mapper;

    public UpdatePaisUseCase(final PaisRepository repository, final PaisApplicationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<PaisResponse> execute(final PaisUpdateRequest request) {
        return repository.findById(request.id())
                .switchIfEmpty(Mono.error(new ApplicationException("Pais no encontrado")))
                .flatMap(existing -> {
                    final Pais actualizado = mapper.toDomain(request);
                    return repository.save(actualizado);
                }).map(mapper::toResponse);
    }
}
