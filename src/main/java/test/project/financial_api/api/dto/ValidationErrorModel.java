package test.project.financial_api.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


@Schema(description = "Модель ошибки при валидации")
public record ValidationErrorModel(
  @Schema(description = "Код ошибки", example = "1000")
  int code,
  @Schema(description = "Текст ошибки", example = "Ошибка валидации")
  String message,
  @Schema(description = "список полей с ошибками")
  List<Error> errors
) {
  
  
  @Schema(description = "Свзяка с конкретным указанием ошибки")
  public record Error(
    @Schema(description = "Название поля", example = "name")
    String field,
    @Schema(description = "Текст ошибки", example = "Ошибка валидации")
    String message
  ) {
  
  }
}
