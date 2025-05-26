package test.project.financial_api.api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Запрос на создание пользователя")
public record AppUserCreateRequest(
  @Schema(description = "Имя пользователя в системе",
    example = "OkabeRintaro", minLength = 3, maxLength = 64, required = true)
  @Size(min = 3, max = 64, message = "Имя пользователя требует длину от 3 до 64")
  @NotBlank(message = "Имя пользователя не может быть пустыми")
  @Pattern(
    regexp = "^(?=.*[a-z])(?=.*[A-Z])[A-Za-z]+$",
    message = "Имя может состоять только из латинских букв"
  )
  String name,
  
  @Schema(description = """
    Пароль должен содержать:
         - хотя бы одну заглавную латинскую букву,
         - хотя бы одну строчную латинскую букву,
         - хотя бы одну цифру
    """,
    example = "Qwerty123", minLength = 8, maxLength = 64, required = true)
  @Size(min = 8, max = 64, message = "Пароль требует длину от 8 до 64")
  @NotBlank(message = "Пароль не может быть пустыми")
  @Pattern(
    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]+$",
    message = "Пароль должен содержать хотя бы одну заглавную и строчную латинскую букву и цифру, без специальных символов"
  )
  String password,
  
  @Schema(description = "Начальный баланс, с которым создаётся учётная запись", example = "1000", required = true)
  @Min(value = 0, message = "Начальный баланс не может быть отрицательным")
  long balance
) {
}
