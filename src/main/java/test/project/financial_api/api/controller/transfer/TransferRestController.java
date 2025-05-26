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
import test.project.financial_api.mapper.TransferMapper;
import test.project.financial_api.service.TransferService;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/v1/transfer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransferRestController implements TransferController {
  
  TransferService transferService;
  TransferMapper transferMapper;
  
  @Override
  public ResponseEntity<TransferInfoResponse> transfer(final TransferRequest request) {
    return ResponseEntity.ok(
      transferMapper.fromEntityToDto(
        transferService.transfer(request)
      )
    );
  }
  
  @Override
  public ResponseEntity<PageResponse<TransferInfoResponse>> getTransfersByUser(final Pageable pageable) {
    return ResponseEntity.ok(
      transferMapper.pageResponse(
        transferService.getTransfersByUser(pageable)
      )
    );
  }
  
  @Override
  public ResponseEntity<PageResponse<TransferInfoResponse>> getAllTransfers(final Pageable pageable) {
    return ResponseEntity.ok(
      transferMapper.pageResponse(
        transferService.getAll(pageable)
      )
    );
  }
  
  @Override
  public ResponseEntity<PageResponse<TransferInfoResponse>> getTransfersByDateRange(final LocalDateTime from, final LocalDateTime to,
                                                                                    final Pageable pageable) {
    return ResponseEntity.ok(
      transferMapper.pageResponse(
        transferService.getTransfersByDateRange(from, to, pageable)
      )
    );
  }
  
  
}
