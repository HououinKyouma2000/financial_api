package test.project.financial_api.persistence.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import test.project.financial_api.persistence.entity.AccountEntity;

import java.util.Optional;
import java.util.UUID;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, UUID> {
  @Lock(LockModeType.PESSIMISTIC_READ)
  @Query("select a from AccountEntity a where a.id = ?1")
  Optional<AccountEntity> findByIdForUpdate(UUID id);
}
