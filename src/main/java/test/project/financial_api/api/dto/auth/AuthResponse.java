package test.project.financial_api.api.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ на аутентификацию")
public record AuthResponse(
  @Schema(
    description = "JWT токен, используемый для аутентификации в системе",
    example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  )
  String token
) {
}
