package test.project.financial_api.api.dto.transfer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Запрос на создание перевода между счетами")
public record TransferRequest(
  @Schema(
    description = "Id счета отправителя",
    example = "d290f1ee-6c54-4b01-90e6-d701748f0851",
    required = true
  )
  @NotNull(message = "senderAccount не может быть null")
  UUID senderAccount,
  
  @Schema(
    description = "Id счета получателя",
    example = "5a3e2f84-8a2e-4d7f-b39f-79a442a7b7c3",
    required = true
  )
  @NotNull(message = "recipientAccount не может быть null")
  UUID recipientAccount,
  
  @Schema(
    description = "Сумма перевода в тинах/центах/копейках (или минимальной единице валюты), должна быть больше 0",
    example = "1000",
    required = true
  )
  @Min(value = 1, message = "Сумма перевода должна быть больше 0")
  long amount
) {

}

