package co.edu.uco.backendvictus.application.usecase.ciudad;

import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.backendvictus.application.dto.ciudad.CiudadResponse;
import co.edu.uco.backendvictus.application.dto.ciudad.CiudadUpdateRequest;
import co.edu.uco.backendvictus.application.dto.common.ChangeResponseDTO;
import co.edu.uco.backendvictus.application.mapper.CiudadApplicationMapper;
import co.edu.uco.backendvictus.application.usecase.UseCase;
import co.edu.uco.backendvictus.crosscutting.exception.ApplicationException;
import co.edu.uco.backendvictus.domain.model.Ciudad;
import co.edu.uco.backendvictus.domain.model.Departamento;
import co.edu.uco.backendvictus.domain.port.CiudadRepository;
import co.edu.uco.backendvictus.domain.port.DepartamentoRepository;
import reactor.core.publisher.Mono;

@Service
public class UpdateCiudadUseCase implements UseCase<CiudadUpdateRequest, ChangeResponseDTO<CiudadResponse>> {

    private final CiudadRepository ciudadRepository;
    private final DepartamentoRepository departamentoRepository;
    private final CiudadApplicationMapper mapper;

    public UpdateCiudadUseCase(final CiudadRepository ciudadRepository,
            final DepartamentoRepository departamentoRepository, final CiudadApplicationMapper mapper) {
        this.ciudadRepository = ciudadRepository;
        this.departamentoRepository = departamentoRepository;
        this.mapper = mapper;
    }

    @Override
    public Mono<ChangeResponseDTO<CiudadResponse>> execute(final CiudadUpdateRequest request) {
        return ciudadRepository.findById(request.id())
                .switchIfEmpty(Mono.error(new ApplicationException("Ciudad no encontrada")))
                .flatMap(existente -> departamentoRepository.findById(request.departamentoId())
                        .switchIfEmpty(Mono.error(new ApplicationException("Departamento no encontrado")))
                        .flatMap(departamento -> {
                            final Ciudad actualizado = existente.update(request.nombre(), departamento,
                                    request.activo());
                            return ensureNombreDisponible(actualizado.getNombre(), existente.getId())
                                    .then(Mono.defer(() -> {
                                        final CiudadResponse before = mapper.toResponse(existente);
                                        return ciudadRepository.save(actualizado)
                                                .map(mapper::toResponse)
                                                .map(after -> ChangeResponseDTO.of(before, after));
                                    }));
                        }));
    }

    private Mono<Void> ensureNombreDisponible(final String nombre, final UUID excluirId) {
        return ciudadRepository.findByNombreIgnoreCase(nombre)
                .filter(existente -> excluirId == null || !existente.getId().equals(excluirId))
                .flatMap(existente -> Mono.<Void>error(
                        new ApplicationException("Ya existe una ciudad con el nombre especificado")))
                .switchIfEmpty(Mono.empty());
    }
}
