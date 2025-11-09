package co.edu.uco.backendvictus.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uco.backendvictus.infrastructure.persistence.entity.AdministradorJpaEntity;

public interface AdministradorJpaRepository extends JpaRepository<AdministradorJpaEntity, UUID> {
}
