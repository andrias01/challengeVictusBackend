package co.edu.uco.backendvictus.infrastructure.secondary.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("pais")
public class PaisEntity {

    @Id
    private UUID id;

    @Column("nombre")
    private String nombre;

    @Column("activo")
    private boolean activo;

    public PaisEntity() {
        // R2DBC requires a default constructor
    }

    public PaisEntity(final UUID id, final String nombre, final boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(final boolean activo) {
        this.activo = activo;
    }
}
