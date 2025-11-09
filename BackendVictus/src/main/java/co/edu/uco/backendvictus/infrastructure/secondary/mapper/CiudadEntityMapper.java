package co.edu.uco.backendvictus.infrastructure.secondary.mapper;

import org.springframework.stereotype.Component;

import co.edu.uco.backendvictus.domain.model.Ciudad;
import co.edu.uco.backendvictus.domain.model.Departamento;
import co.edu.uco.backendvictus.infrastructure.secondary.entity.CiudadEntity;

@Component
public class CiudadEntityMapper {

    public CiudadEntity toEntity(final Ciudad ciudad) {
        if (ciudad == null) {
            return null;
        }
        return new CiudadEntity(ciudad.getId(), ciudad.getDepartamento().getId(), ciudad.getNombre(),
                ciudad.isActivo());
    }

    public Ciudad toDomain(final CiudadEntity entity, final Departamento departamento) {
        if (entity == null || departamento == null) {
            return null;
        }
        return Ciudad.create(entity.getId(), entity.getNombre(), departamento, entity.isActivo());
    }
}
