package co.edu.uco.backendvictus.infrastructure.api.handler;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, String message, String path) {
}
