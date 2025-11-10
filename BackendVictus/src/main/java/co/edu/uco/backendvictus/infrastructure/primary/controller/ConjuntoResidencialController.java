package co.edu.uco.backendvictus.infrastructure.primary.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.backendvictus.application.dto.conjunto.ConjuntoCreateRequest;
import co.edu.uco.backendvictus.application.dto.conjunto.ConjuntoResponse;
import co.edu.uco.backendvictus.application.dto.conjunto.ConjuntoUpdateRequest;
import co.edu.uco.backendvictus.application.usecase.conjunto.CreateConjuntoUseCase;
import co.edu.uco.backendvictus.application.usecase.conjunto.DeleteConjuntoUseCase;
import co.edu.uco.backendvictus.application.usecase.conjunto.ListConjuntoUseCase;
import co.edu.uco.backendvictus.application.usecase.conjunto.UpdateConjuntoUseCase;
import co.edu.uco.backendvictus.crosscutting.helpers.BooleanHelper;
import co.edu.uco.backendvictus.crosscutting.helpers.StringSanitizer;
import co.edu.uco.backendvictus.crosscutting.helpers.TextHelper;
import co.edu.uco.backendvictus.crosscutting.helpers.UUIDHelper;
import co.edu.uco.backendvictus.domain.model.filter.ConjuntoResidencialFilter;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/conjuntos")
@Validated
public class ConjuntoResidencialController {

    private final CreateConjuntoUseCase createConjuntoUseCase;
    private final ListConjuntoUseCase listConjuntoUseCase;
    private final UpdateConjuntoUseCase updateConjuntoUseCase;
    private final DeleteConjuntoUseCase deleteConjuntoUseCase;

    public ConjuntoResidencialController(final CreateConjuntoUseCase createConjuntoUseCase,
            final ListConjuntoUseCase listConjuntoUseCase, final UpdateConjuntoUseCase updateConjuntoUseCase,
            final DeleteConjuntoUseCase deleteConjuntoUseCase) {
        this.createConjuntoUseCase = createConjuntoUseCase;
        this.listConjuntoUseCase = listConjuntoUseCase;
        this.updateConjuntoUseCase = updateConjuntoUseCase;
        this.deleteConjuntoUseCase = deleteConjuntoUseCase;
    }

    @PostMapping("/crear")
    public Mono<ResponseEntity<ConjuntoResponse>> crear(@Valid @RequestBody final ConjuntoCreateRequest request) {
        final ConjuntoCreateRequest sanitized = new ConjuntoCreateRequest(request.ciudadId(), request.administradorId(),
                StringSanitizer.sanitize(request.nombre()), StringSanitizer.sanitize(request.direccion()),
                request.activo());
        return createConjuntoUseCase.execute(sanitized)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

    @GetMapping
    public Flux<ConjuntoResponse> listar(@RequestParam(value = "nombre", required = false) final String nombre,
            @RequestParam(value = "ciudadId", required = false) final UUID ciudadId,
            @RequestParam(value = "administradorId", required = false) final UUID administradorId,
            @RequestParam(value = "activo", required = false) final Boolean activo) {
        final ConjuntoResidencialFilter filter = new ConjuntoResidencialFilter(nombre, ciudadId, administradorId,
                activo);
        return listConjuntoUseCase.execute(filter);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ConjuntoResponse>> actualizar(@PathVariable("id") final UUID id,
            @Valid @RequestBody final ConjuntoUpdateRequest request) {
        final ConjuntoUpdateRequest sanitized = new ConjuntoUpdateRequest(id, request.ciudadId(),
                request.administradorId(), StringSanitizer.sanitize(request.nombre()),
                StringSanitizer.sanitize(request.direccion()), request.activo());
        return updateConjuntoUseCase.execute(sanitized).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable("id") final UUID id) {
        return deleteConjuntoUseCase.execute(id).thenReturn(ResponseEntity.noContent().build());
    }

    @GetMapping("/dummy")
    public Mono<ResponseEntity<ConjuntoResponse>> dummy() {
        final ConjuntoResponse response = new ConjuntoResponse(UUIDHelper.getDefault(), UUIDHelper.getDefault(),
                UUIDHelper.getDefault(), TextHelper.EMPTY, TextHelper.EMPTY, BooleanHelper.DEFAULT);
        return Mono.just(ResponseEntity.ok(response));
    }
}
