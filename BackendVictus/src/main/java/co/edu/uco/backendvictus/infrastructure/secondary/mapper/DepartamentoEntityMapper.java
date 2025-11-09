package co.edu.uco.backendvictus.infrastructure.secondary.mapper;

import org.springframework.stereotype.Component;

import co.edu.uco.backendvictus.crosscutting.helpers.ObjectHelper;
import co.edu.uco.backendvictus.crosscutting.helpers.TextHelper;
import co.edu.uco.backendvictus.domain.model.Departamento;
import co.edu.uco.backendvictus.domain.model.Pais;
import co.edu.uco.backendvictus.infrastructure.secondary.entity.DepartamentoEntity;

@Component
public class DepartamentoEntityMapper {

    public DepartamentoEntity toEntity(final Departamento departamento) {
        if (ObjectHelper.isNull(departamento)) {
            return null;
        }
        return new DepartamentoEntity(departamento.getId(), departamento.getPais().getId(),
                TextHelper.applyTrim(departamento.getNombre()), departamento.isActivo());
    }

    public Departamento toDomain(final DepartamentoEntity entity, final Pais pais) {
        if (ObjectHelper.isNull(entity) || ObjectHelper.isNull(pais)) {
            return null;
        }
        return Departamento.create(entity.getId(), entity.getNombre(), pais, entity.isActivo());
    }
}
