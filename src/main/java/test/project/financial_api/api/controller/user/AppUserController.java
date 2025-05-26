package test.project.financial_api.api.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import test.project.financial_api.api.dto.AppUserInfoResponse;
import test.project.financial_api.api.dto.ErrorModel;
import test.project.financial_api.api.dto.ValidationErrorModel;
import test.project.financial_api.api.dto.user.AppUserCreateRequest;
import test.project.financial_api.api.dto.user.AppUserUpdateRequest;

@ApiResponses(value = {
  @ApiResponse(responseCode = "200", description = "Success"),
  @ApiResponse(responseCode = "400", description = "ErrorModel",
    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
      schema = @Schema(implementation = ErrorModel.class))}),
  @ApiResponse(responseCode = "422", description = "ValidationErrorModel",
    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
      schema = @Schema(implementation = ValidationErrorModel.class))})
})

@Tag(name = "App user", description = "Методы работы с пользователям приложения")
public interface AppUserController {
  @PostMapping
  @Operation(summary = "Create", description = "Создание учетной записи пользователя")
  ResponseEntity<AppUserInfoResponse> create(@RequestBody @Valid AppUserCreateRequest appUserCreateRequest);
  
  @GetMapping
  @Operation(summary = "Info", description = "Информация пользователя")
  ResponseEntity<AppUserInfoResponse> get();
  
  @PutMapping
  @Operation(summary = "Update user data", description = "Обновление данных пользователя")
  ResponseEntity<AppUserInfoResponse> update(@RequestBody @Valid AppUserUpdateRequest appUserUpdateRequest);
}
