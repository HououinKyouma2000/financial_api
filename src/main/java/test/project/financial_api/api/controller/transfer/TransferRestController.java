package test.project.financial_api.api.controller.transfer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.project.financial_api.api.dto.PageResponse;
import test.project.financial_api.api.dto.transfer.TransferInfoResponse;
import test.project.financial_api.api.dto.transfer.TransferRequest;
import test.project.financial_api.service.TransferService;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/v1/transfer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransferRestController implements TransferController {
  
  TransferService transferService;
  
  @Override
  public ResponseEntity<TransferInfoResponse> transfer(final TransferRequest request) {
    return ResponseEntity.ok(
      
      transferService.transfer(request)
    
    );
  }
  
  @Override
  public ResponseEntity<PageResponse<TransferInfoResponse>> getTransfersByUser(final Pageable pageable) {
    return ResponseEntity.ok(
      
      transferService.getTransfersByUser(pageable)
    
    );
  }
  
  @Override
  public ResponseEntity<PageResponse<TransferInfoResponse>> getAllTransfers(final Pageable pageable) {
    return ResponseEntity.ok(
      
      transferService.getAll(pageable)
    
    );
  }
  
  @Override
  public ResponseEntity<PageResponse<TransferInfoResponse>> getTransfersByDateRange(final LocalDateTime from, final LocalDateTime to,
                                                                                    final Pageable pageable) {
    return ResponseEntity.ok(
      
      transferService.getTransfersByDateRange(from, to, pageable)
    
    );
  }
  
  
}
