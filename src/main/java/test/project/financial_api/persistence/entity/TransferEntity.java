package test.project.financial_api.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transfers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferEntity extends SimpleAbstractEntity {
  
  @ManyToOne(optional = false)
  @JoinColumn(name = "sender_account", nullable = false, updatable = false)
  AccountEntity senderAccount;
  
  @ManyToOne(optional = false)
  @JoinColumn(name = "recipient_account", nullable = false, updatable = false)
  AccountEntity recipientAccount;
  
  @Column(name = "amount", nullable = false)
  long amount;
  
  //TODO добавить extId, status, error - для сохранение всех случаев обращения и идемпотентности
  
  public void creating() {
    super.setId(UUID.randomUUID());
  }
  
}
