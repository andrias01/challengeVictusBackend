package co.edu.uco.backendvictus.infrastructure.primary.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

import co.edu.uco.backendvictus.crosscutting.exception.ApplicationException;
import co.edu.uco.backendvictus.crosscutting.exception.BackendVictusException;
import co.edu.uco.backendvictus.crosscutting.exception.DomainException;
import co.edu.uco.backendvictus.crosscutting.exception.InfrastructureException;
import co.edu.uco.backendvictus.crosscutting.helpers.LoggerHelper;
import co.edu.uco.backendvictus.crosscutting.helpers.TextHelper;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final org.slf4j.Logger LOGGER = LoggerHelper.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(DomainException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleDomainException(final DomainException exception,
            final ServerWebExchange exchange) {
        LOGGER.warn("Error de dominio: {}", exception.getTechnicalMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, exception, exchange);
    }

    @ExceptionHandler(ApplicationException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleApplicationException(final ApplicationException exception,
            final ServerWebExchange exchange) {
        LOGGER.warn("Error de aplicacion: {}", exception.getTechnicalMessage());
        return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, exception, exchange);
    }

    @ExceptionHandler(InfrastructureException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleInfrastructureException(final InfrastructureException exception,
            final ServerWebExchange exchange) {
        LOGGER.error("Error de infraestructura: {}", exception.getTechnicalMessage(), exception);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception, exchange);
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(final Exception exception,
            final ServerWebExchange exchange) {
        LOGGER.error("Error inesperado", exception);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception, exchange);
    }

    private Mono<ResponseEntity<ErrorResponse>> buildResponse(final HttpStatus status, final Exception exception,
            final ServerWebExchange exchange) {
        final String message = exception instanceof BackendVictusException backendException
                ? backendException.getUserMessage()
                : TextHelper.applyTrim(exception.getMessage());
        final String path = exchange.getRequest().getURI().getPath();
        final ErrorResponse response = new ErrorResponse(LocalDateTime.now(), message, path);
        return Mono.just(ResponseEntity.status(status).body(response));
    }
}
