package test.project.financial_api.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Общие данные по пользователю")
public record AppUserInfoResponse(
  @Schema(description = "id пользователя", example = "d8267560-21a0-4904-b3ab-2879651874ce")
  UUID id,
  @Schema(description = "Имя пользователя в системе",
    example = "OkabeRintaro", minLength = 3, maxLength = 64, required = true)
  @Size(min = 3, max = 64, message = "Имя пользователя требует длину от 3 до 64")
  @NotBlank(message = "Имя пользователя не может быть пустым")
  @Pattern(
    regexp = "^(?=.*[a-z])(?=.*[A-Z])[A-Za-z]+$",
    message = "Имя может состоять только из латинских букв и должно содержать как минимум одну строчную и одну заглавную букву"
  )
  String name,
  
  @Schema(description = "Общие данные по счету")
  AccountInfoResponse account,
  
  @Schema(description = "Время создания записи")
  LocalDateTime createdAt,
  
  @Schema(description = "Время изменения записи")
  LocalDateTime updatedAt
) {
}
