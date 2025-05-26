package test.project.financial_api.service;

import test.project.financial_api.persistence.entity.AccountEntity;

import java.util.UUID;

public interface AccountService {
  
  AccountEntity create(long balance);
  
  AccountEntity debit(UUID accountId, long amount);
  
  AccountEntity credit(UUID accountId, long amount);
}
