package test.project.financial_api.api.controller.transfer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import test.project.financial_api.api.dto.ErrorModel;
import test.project.financial_api.api.dto.PageResponse;
import test.project.financial_api.api.dto.ValidationErrorModel;
import test.project.financial_api.api.dto.transfer.TransferInfoResponse;
import test.project.financial_api.api.dto.transfer.TransferRequest;

import java.time.LocalDateTime;

@ApiResponses(value = {
  @ApiResponse(responseCode = "200", description = "Success"),
  @ApiResponse(responseCode = "400", description = "ErrorModel",
    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
      schema = @Schema(implementation = ErrorModel.class))}),
  @ApiResponse(responseCode = "422", description = "ValidationErrorModel",
    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
      schema = @Schema(implementation = ValidationErrorModel.class))})
})

@Tag(name = "Transfer operations", description = "Методы по денежным переводам")
public interface TransferController {
  
  @PostMapping
  @Operation(summary = "Transfer", description = "Проведения трансфера")
  ResponseEntity<TransferInfoResponse> transfer(@RequestBody @Valid TransferRequest request);
  
  @GetMapping
  @Operation(summary = "All transfer by user", description = "Трансферы по пользователю")
  ResponseEntity<PageResponse<TransferInfoResponse>> getTransfersByUser(
    @ParameterObject
    @PageableDefault(page = 0, size = 100, sort = "createdAt", direction = Sort.Direction.DESC)
    Pageable pageable);
  
  @GetMapping("/all")
  @Operation(summary = "All transfer", description = "Трансферы все без разделения по пользователю")
  ResponseEntity<PageResponse<TransferInfoResponse>> getAllTransfers(
    @ParameterObject
    @PageableDefault(page = 0, size = 100, sort = "createdAt", direction = Sort.Direction.DESC)
    Pageable pageable);
  
  @GetMapping("/by-date")
  @Operation(summary = "Transfer by date range", description = "Трансферы по датам")
  ResponseEntity<PageResponse<TransferInfoResponse>> getTransfersByDateRange(
    @RequestParam(value = "from", required = false)
    @Parameter(description = "Дата начала диапазона в формате yyyy-MM-dd'T'HH:mm:ss", example = "2024-01-01T00:00:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime from,
    
    @RequestParam(value = "to", required = false)
    @Parameter(description = "Дата конца диапазона в формате yyyy-MM-dd'T'HH:mm:ss", example = "2024-01-01T00:00:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime to,
    
    @ParameterObject
    @PageableDefault(page = 0, size = 100, sort = "createdAt", direction = Sort.Direction.DESC)
    Pageable pageable
  );
}
