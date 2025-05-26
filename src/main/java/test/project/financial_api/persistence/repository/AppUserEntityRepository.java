package test.project.financial_api.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.project.financial_api.persistence.entity.AppUserEntity;

import java.util.Optional;
import java.util.UUID;

public interface AppUserEntityRepository extends JpaRepository<AppUserEntity, UUID> {
  Optional<AppUserEntity> findByName(String name);
}
