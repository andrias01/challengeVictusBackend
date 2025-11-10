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

import co.edu.uco.backendvictus.application.dto.administrador.AdministradorCreateRequest;
import co.edu.uco.backendvictus.application.dto.administrador.AdministradorResponse;
import co.edu.uco.backendvictus.application.dto.administrador.AdministradorUpdateRequest;
import co.edu.uco.backendvictus.application.usecase.administrador.CreateAdministradorUseCase;
import co.edu.uco.backendvictus.application.usecase.administrador.DeleteAdministradorUseCase;
import co.edu.uco.backendvictus.application.usecase.administrador.ListAdministradorUseCase;
import co.edu.uco.backendvictus.application.usecase.administrador.UpdateAdministradorUseCase;
import co.edu.uco.backendvictus.crosscutting.helpers.BooleanHelper;
import co.edu.uco.backendvictus.crosscutting.helpers.StringSanitizer;
import co.edu.uco.backendvictus.crosscutting.helpers.TextHelper;
import co.edu.uco.backendvictus.crosscutting.helpers.UUIDHelper;
import co.edu.uco.backendvictus.domain.model.filter.AdministradorFilter;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/administradores")
@Validated
public class AdministradorController {

    private final CreateAdministradorUseCase createAdministradorUseCase;
    private final ListAdministradorUseCase listAdministradorUseCase;
    private final UpdateAdministradorUseCase updateAdministradorUseCase;
    private final DeleteAdministradorUseCase deleteAdministradorUseCase;

    public AdministradorController(final CreateAdministradorUseCase createAdministradorUseCase,
            final ListAdministradorUseCase listAdministradorUseCase,
            final UpdateAdministradorUseCase updateAdministradorUseCase,
            final DeleteAdministradorUseCase deleteAdministradorUseCase) {
        this.createAdministradorUseCase = createAdministradorUseCase;
        this.listAdministradorUseCase = listAdministradorUseCase;
        this.updateAdministradorUseCase = updateAdministradorUseCase;
        this.deleteAdministradorUseCase = deleteAdministradorUseCase;
    }

    @PostMapping("/crear")
    public Mono<ResponseEntity<AdministradorResponse>> crear(@Valid @RequestBody final AdministradorCreateRequest request) {
        final AdministradorCreateRequest sanitized = new AdministradorCreateRequest(
                StringSanitizer.sanitize(request.primerNombre()), StringSanitizer.sanitize(request.segundoNombres()),
                StringSanitizer.sanitize(request.primerApellido()), StringSanitizer.sanitize(request.segundoApellido()),
                StringSanitizer.sanitize(request.email()), StringSanitizer.sanitize(request.telefono()), request.activo());
        return createAdministradorUseCase.execute(sanitized)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

    @GetMapping
    public Flux<AdministradorResponse> listar(@RequestParam(value = "nombre", required = false) final String nombre,
            @RequestParam(value = "email", required = false) final String email,
            @RequestParam(value = "activo", required = false) final Boolean activo) {
        final AdministradorFilter filter = new AdministradorFilter(nombre, email, activo);
        return listAdministradorUseCase.execute(filter);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<AdministradorResponse>> actualizar(@PathVariable("id") final UUID id,
            @Valid @RequestBody final AdministradorUpdateRequest request) {
        final AdministradorUpdateRequest sanitized = new AdministradorUpdateRequest(id,
                StringSanitizer.sanitize(request.primerNombre()), StringSanitizer.sanitize(request.segundoNombres()),
                StringSanitizer.sanitize(request.primerApellido()), StringSanitizer.sanitize(request.segundoApellido()),
                StringSanitizer.sanitize(request.email()), StringSanitizer.sanitize(request.telefono()),
                request.activo());
        return updateAdministradorUseCase.execute(sanitized).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable("id") final UUID id) {
        return deleteAdministradorUseCase.execute(id).thenReturn(ResponseEntity.noContent().build());
    }

    @GetMapping("/dummy")
    public Mono<ResponseEntity<AdministradorResponse>> dummy() {
        final AdministradorResponse response = new AdministradorResponse(UUIDHelper.getDefault(), TextHelper.EMPTY,
                TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY,
                BooleanHelper.DEFAULT);
        return Mono.just(ResponseEntity.ok(response));
    }
}
