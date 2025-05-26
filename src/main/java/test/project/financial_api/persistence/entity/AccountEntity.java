package test.project.financial_api.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import test.project.financial_api.expection.InsufficientFundsException;

import java.util.UUID;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity extends SimpleAbstractEntity {
  
  @Column(name = "balance", nullable = false)
  long balance;
  
  public static AccountEntity initialize(final long balance) {
    final AccountEntity entity = new AccountEntity();
    entity.setId(UUID.randomUUID());
    entity.balance = balance;
    return entity;
  }
  
  public void debit(final long amount) {
    if (amount <= balance) {
      balance -= amount;
    } else {
      throw new InsufficientFundsException();
    }
  }
  
  public void credit(final long amount) {
    balance += amount;
  }
  
}
