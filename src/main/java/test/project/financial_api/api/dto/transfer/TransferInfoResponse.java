package test.project.financial_api.api.dto.transfer;

import io.swagger.v3.oas.annotations.media.Schema;
import test.project.financial_api.api.dto.AccountInfoResponse;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Ответ на создание перевода между счетами")
public record TransferInfoResponse(
  
  @Schema(description = "Id перевода", example = "d8267560-21a0-4904-b3ab-2879651874ce")
  UUID id,
  
  @Schema(description = "Информация о счете отправителя")
  AccountInfoResponse senderAccount,
  
  //TODO сделать модель с меньшим набором данных
  @Schema(description = "Информация о счете получателя")
  AccountInfoResponse recipientAccount,
  
  @Schema(description = "Сумма перевода в тинах/центах/копейках (или минимальной единице валюты), должна быть больше 0",
    example = "1000")
  long amount,
  
  @Schema(description = "Время создания записи")
  LocalDateTime createdAt,
  
  @Schema(description = "Время изменения записи")
  LocalDateTime updatedAt
) {
}

