package test.project.financial_api.api.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import test.project.financial_api.api.dto.auth.AuthRequest;
import test.project.financial_api.api.dto.auth.AuthResponse;
import test.project.financial_api.api.dto.ErrorModel;
import test.project.financial_api.api.dto.ValidationErrorModel;

@ApiResponses(value = {
  @ApiResponse(responseCode = "200", description = "Success"),
  @ApiResponse(responseCode = "400", description = "ErrorModel",
    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
      schema = @Schema(implementation = ErrorModel.class))}),
  @ApiResponse(responseCode = "422", description = "ValidationErrorModel",
    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
      schema = @Schema(implementation = ValidationErrorModel.class))})
})

@Tag(name = "Auth", description = "Методы работы с аутентификацией")
public interface AuthController {
  
  @PostMapping("/login")
  @Operation(summary = "Login", description = "Аутентификация в системе")
  ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest);
  
}
