package co.edu.uco.backendvictus.application.dto.pais;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PaisUpdateRequest(
        @NotNull(message = "El identificador del pais es obligatorio") UUID id,
        @NotBlank(message = "El nombre del pais es obligatorio")
        @Size(max = 120, message = "El nombre del pais no puede superar 120 caracteres") String nombre,
        boolean activo) {
}
