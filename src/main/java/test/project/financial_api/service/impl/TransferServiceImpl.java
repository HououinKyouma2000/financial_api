package test.project.financial_api.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.project.financial_api.api.dto.PageResponse;
import test.project.financial_api.api.dto.transfer.TransferInfoResponse;
import test.project.financial_api.api.dto.transfer.TransferRequest;
import test.project.financial_api.expection.TransferAccountsEqualsExceptions;
import test.project.financial_api.expection.ValidateAccountOwnerException;
import test.project.financial_api.mapper.TransferMapper;
import test.project.financial_api.persistence.entity.AccountEntity;
import test.project.financial_api.persistence.entity.TransferEntity;
import test.project.financial_api.persistence.repository.TransferEntityRepository;
import test.project.financial_api.service.AccountService;
import test.project.financial_api.service.AppUserService;
import test.project.financial_api.service.TransferService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransferServiceImpl implements TransferService {
  
  TransferEntityRepository transferEntityRepository;
  AccountService accountService;
  AppUserService appUserService;
  TransferMapper transferMapper;
  
  
  @Override
  @Transactional
  public TransferInfoResponse transfer(final TransferRequest transferRequest) {
    if (transferRequest.senderAccount().equals(transferRequest.recipientAccount())) {
      throw new TransferAccountsEqualsExceptions();
    }
    
    if (!appUserService.getUserEntityFromSecurityContext().getAccount().getId()
      .equals(transferRequest.senderAccount())) {
      throw new ValidateAccountOwnerException();
    }
    
    final AccountEntity senderAccount = accountService.debit(transferRequest.senderAccount(), transferRequest.amount());
    final AccountEntity recipientAccount = accountService.credit(transferRequest.recipientAccount(), transferRequest.amount());
    
    final TransferEntity transfer = TransferEntity.builder()
      .amount(transferRequest.amount())
      .senderAccount(senderAccount)
      .recipientAccount(recipientAccount)
      .build();
    transfer.creating();
    
    return transferMapper.fromEntityToDto(transferEntityRepository.save(transfer));
  }
  
  @Override
  public PageResponse<TransferInfoResponse> getTransfersByUser(final Pageable pageable) {
    return transferMapper.pageResponse(
      transferEntityRepository.findAllBySenderAccountOrRecipientAccount(
        appUserService.getUserEntityFromSecurityContext().getAccount().getId(), pageable
      )
    );
  }
  
  @Override
  public PageResponse<TransferInfoResponse> getAll(final Pageable pageable) {
    return transferMapper.pageResponse(transferEntityRepository.findAll(pageable));
  }
  
  @Override
  public PageResponse<TransferInfoResponse> getTransfersByDateRange(final LocalDateTime from, final LocalDateTime to, final Pageable pageable) {
    return transferMapper.pageResponse(
      from == null && to == null ? transferEntityRepository.findAll(pageable) :
        from != null && to != null ? transferEntityRepository.findAllByCreatedAtBetween(from, to, pageable) :
          from != null ? transferEntityRepository.findAllByCreatedAtAfter(from, pageable) :
            transferEntityRepository.findAllByCreatedAtBefore(to, pageable)
    );
  }
}
