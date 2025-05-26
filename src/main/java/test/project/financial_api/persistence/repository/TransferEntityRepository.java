package test.project.financial_api.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import test.project.financial_api.persistence.entity.TransferEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TransferEntityRepository extends JpaRepository<TransferEntity, UUID> {
  @Query("select t from TransferEntity t where t.senderAccount.id = ?1 or t.recipientAccount.id = ?1")
  Page<TransferEntity> findAllBySenderAccountOrRecipientAccount(UUID accountId, Pageable pageable);
  
  Page<TransferEntity> findAllByCreatedAtBetween(LocalDateTime createdAtAfter, LocalDateTime createdAtBefore, Pageable pageable);
  
  Page<TransferEntity> findAllByCreatedAtAfter(LocalDateTime createdAtAfter, Pageable pageable);
  
  Page<TransferEntity> findAllByCreatedAtBefore(LocalDateTime createdAtBefore, Pageable pageable);
}
