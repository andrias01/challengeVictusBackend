package co.edu.uco.backendvictus.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uco.backendvictus.infrastructure.persistence.entity.DepartamentoJpaEntity;

public interface DepartamentoJpaRepository extends JpaRepository<DepartamentoJpaEntity, UUID> {
}
