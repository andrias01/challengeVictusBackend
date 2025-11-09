package co.edu.uco.backendvictus.infrastructure.secondary.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("departamento")
public class DepartamentoEntity {

    @Id
    private UUID id;

    @Column("pais_id")
    private UUID paisId;

    @Column("nombre")
    private String nombre;

    @Column("activo")
    private boolean activo;

    public DepartamentoEntity() {
        // R2DBC requires a default constructor
    }

    public DepartamentoEntity(final UUID id, final UUID paisId, final String nombre, final boolean activo) {
        this.id = id;
        this.paisId = paisId;
        this.nombre = nombre;
        this.activo = activo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public UUID getPaisId() {
        return paisId;
    }

    public void setPaisId(final UUID paisId) {
        this.paisId = paisId;
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
