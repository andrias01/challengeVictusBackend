package co.edu.uco.backendvictus.infrastructure.secondary.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.edu.uco.backendvictus.infrastructure.secondary.entity.DepartamentoEntity;

public interface DepartamentoReactiveRepository extends ReactiveCrudRepository<DepartamentoEntity, UUID> {
}
