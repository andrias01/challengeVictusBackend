package co.edu.uco.backendvictus.infrastructure.secondary.mapper;

import org.springframework.stereotype.Component;

import co.edu.uco.backendvictus.domain.model.Departamento;
import co.edu.uco.backendvictus.domain.model.Pais;
<<<<<<< HEAD
import co.edu.uco.backendvictus.infrastructure.secondary.entity.DepartamentoJpaEntity;

=======
import co.edu.uco.backendvictus.infrastructure.secondary.entity.DepartamentoEntity;
>>>>>>> e0d19b829e9b2fc6b11580c19e3126a1f85fb997

@Component
public class DepartamentoEntityMapper {

<<<<<<< HEAD
    @Autowired
    private PaisEntityMapper paisEntityMapper;

    public abstract DepartamentoJpaEntity toEntity(Departamento departamento);

    public Departamento toDomain(final DepartamentoJpaEntity entity) {
        if (entity == null) {
=======
    public DepartamentoEntity toEntity(final Departamento departamento) {
        if (departamento == null) {
            return null;
        }
        return new DepartamentoEntity(departamento.getId(), departamento.getPais().getId(),
                departamento.getNombre(), departamento.isActivo());
    }

    public Departamento toDomain(final DepartamentoEntity entity, final Pais pais) {
        if (entity == null || pais == null) {
>>>>>>> e0d19b829e9b2fc6b11580c19e3126a1f85fb997
            return null;
        }
        return Departamento.create(entity.getId(), entity.getNombre(), pais, entity.isActivo());
    }
}
