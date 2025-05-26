package test.project.financial_api.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.project.financial_api.expection.AccountNotFoundException;
import test.project.financial_api.persistence.entity.AccountEntity;
import test.project.financial_api.persistence.repository.AccountEntityRepository;
import test.project.financial_api.service.AccountService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
  
  AccountEntityRepository accountEntityRepository;
  
  @Override
  public AccountEntity create(final long balance) {
    final AccountEntity accountEntity = AccountEntity.initialize(balance);
    return accountEntityRepository.save(accountEntity);
  }
  
  @Override
  @Transactional
  public AccountEntity debit(final UUID accountId, final long amount) {
    final AccountEntity account = accountEntityRepository.findByIdForUpdate(accountId).orElseThrow(AccountNotFoundException::new);
    account.debit(amount);
    return accountEntityRepository.save(account);
  }
  
  @Override
  @Transactional
  public AccountEntity credit(final UUID accountId, final long amount) {
    final AccountEntity account = accountEntityRepository.findByIdForUpdate(accountId).orElseThrow(AccountNotFoundException::new);
    account.credit(amount);
    return accountEntityRepository.save(account);
  }
  
}
