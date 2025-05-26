package test.project.financial_api.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель ошибки")
public record ErrorModel(
  @Schema(description = "Код ошибки", example = "1000")
  int code,
  @Schema(description = "Текст ошибки", example = "Ошибка валидации")
  String message
) {
}
