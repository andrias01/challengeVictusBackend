package co.edu.uco.backendvictus.application.dto.administrador;

import java.util.UUID;

public record AdministradorResponse(UUID id, String nombreCompleto, String email, String telefono, boolean activo) {
}
