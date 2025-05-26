package test.project.financial_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import test.project.financial_api.api.dto.transfer.TransferRequest;
import test.project.financial_api.persistence.entity.TransferEntity;

import java.time.LocalDateTime;

public interface TransferService {
  TransferEntity transfer(TransferRequest transferRequest);
  
  Page<TransferEntity> getTransfersByUser(Pageable pageable);
  
  Page<TransferEntity> getAll(Pageable pageable);
  
  Page<TransferEntity> getTransfersByDateRange(LocalDateTime from, LocalDateTime to, Pageable pageable);
}
