package co.edu.uco.backendvictus.infrastructure.secondary.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ciudad")
public class CiudadEntity {

    @Id
    private UUID id;

    @Column("departamento_id")
    private UUID departamentoId;

    @Column("nombre")
    private String nombre;

    @Column("activo")
    private boolean activo;

    public CiudadEntity() {
        // R2DBC requires a default constructor
    }

    public CiudadEntity(final UUID id, final UUID departamentoId, final String nombre, final boolean activo) {
        this.id = id;
        this.departamentoId = departamentoId;
        this.nombre = nombre;
        this.activo = activo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public UUID getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(final UUID departamentoId) {
        this.departamentoId = departamentoId;
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
