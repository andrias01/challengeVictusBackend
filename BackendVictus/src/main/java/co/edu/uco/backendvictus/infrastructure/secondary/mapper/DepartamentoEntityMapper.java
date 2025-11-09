package co.edu.uco.backendvictus.infrastructure.secondary.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.uco.backendvictus.domain.model.Departamento;
import co.edu.uco.backendvictus.domain.model.Pais;
import co.edu.uco.backendvictus.infrastructure.secondary.entity.DepartamentoJpaEntity;


@Mapper(componentModel = "spring", uses = PaisEntityMapper.class)
public abstract class DepartamentoEntityMapper {

    @Autowired
    private PaisEntityMapper paisEntityMapper;

    public abstract DepartamentoJpaEntity toEntity(Departamento departamento);

    public Departamento toDomain(final DepartamentoJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        final Pais pais = paisEntityMapper.toDomain(entity.getPais());
        return Departamento.create(entity.getId(), entity.getNombre(), pais, entity.isActivo());
    }
}
