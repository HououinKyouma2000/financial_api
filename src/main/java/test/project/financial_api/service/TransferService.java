package test.project.financial_api.service;

import org.springframework.data.domain.Pageable;
import test.project.financial_api.api.dto.PageResponse;
import test.project.financial_api.api.dto.transfer.TransferInfoResponse;
import test.project.financial_api.api.dto.transfer.TransferRequest;

import java.time.LocalDateTime;

public interface TransferService {
  TransferInfoResponse transfer(TransferRequest transferRequest);
  
  PageResponse<TransferInfoResponse> getTransfersByUser(Pageable pageable);
  
  PageResponse<TransferInfoResponse> getAll(Pageable pageable);
  
  PageResponse<TransferInfoResponse> getTransfersByDateRange(LocalDateTime from, LocalDateTime to, Pageable pageable);
}
