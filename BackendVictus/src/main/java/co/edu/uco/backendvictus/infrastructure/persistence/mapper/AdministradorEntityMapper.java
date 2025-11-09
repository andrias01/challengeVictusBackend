package co.edu.uco.backendvictus.infrastructure.persistence.mapper;

import co.edu.uco.backendvictus.domain.model.Administrador;
import co.edu.uco.backendvictus.infrastructure.persistence.entity.AdministradorJpaEntity;

public final class AdministradorEntityMapper {

    private AdministradorEntityMapper() {
    }

    public static AdministradorJpaEntity toEntity(final Administrador administrador) {
        return new AdministradorJpaEntity(administrador.getId(), administrador.getNombreCompleto(),
                administrador.getEmail(), administrador.getTelefono(), administrador.isActivo());
    }

    public static Administrador toDomain(final AdministradorJpaEntity entity) {
        return Administrador.create(entity.getId(), entity.getNombreCompleto(), entity.getEmail(),
                entity.getTelefono(), entity.isActivo());
    }
}
