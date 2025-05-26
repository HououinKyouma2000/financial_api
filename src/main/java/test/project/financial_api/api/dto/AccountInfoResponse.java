package test.project.financial_api.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link  test.project.financial_api.persistence.entity.AccountEntity}
 */

@Schema(description = "Общие данные по счету")
public record AccountInfoResponse(
  @Schema(description = "id счета", example = "d8267560-21a0-4904-b3ab-2879651874ce")
  UUID id,
  
  @Schema(description = "Сумма на счету тинах/центах/копейках (или минимальной единице валюты)", example = "1000")
  long balance,
  
  @Schema(description = "Время создания записи")
  LocalDateTime createdAt,
  
  @Schema(description = "Время изменения записи")
  LocalDateTime updatedAt
) {
}
