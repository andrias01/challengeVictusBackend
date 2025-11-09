package co.edu.uco.backendvictus.domain.model;

import java.util.UUID;

import co.edu.uco.backendvictus.crosscutting.helpers.ValidationUtils;

/**
 * Administrator aggregate.
 */
public final class Administrador {

    private final UUID id;
    private final String nombreCompleto;
    private final String email;
    private final String telefono;
    private final boolean activo;

    private Administrador(final UUID id, final String nombreCompleto, final String email, final String telefono,
            final boolean activo) {
        this.id = ValidationUtils.validateUUID(id, "Id del administrador");
        this.nombreCompleto = ValidationUtils.validateRequiredText(nombreCompleto, "Nombre del administrador", 150);
        this.email = ValidationUtils.validateEmail(email);
        this.telefono = ValidationUtils.validateOptionalText(telefono, "Telefono", 20);
        this.activo = activo;
    }

    public static Administrador create(final UUID id, final String nombreCompleto, final String email,
            final String telefono, final boolean activo) {
        return new Administrador(id, nombreCompleto, email, telefono, activo);
    }

    public Administrador update(final String nombreCompleto, final String email, final String telefono,
            final boolean activo) {
        return new Administrador(this.id, nombreCompleto, email, telefono, activo);
    }

    public UUID getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public boolean isActivo() {
        return activo;
    }
}
