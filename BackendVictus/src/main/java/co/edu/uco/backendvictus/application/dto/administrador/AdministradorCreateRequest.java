package co.edu.uco.backendvictus.application.dto.administrador;

public record AdministradorCreateRequest(String nombreCompleto, String email, String telefono, boolean activo) {
}
