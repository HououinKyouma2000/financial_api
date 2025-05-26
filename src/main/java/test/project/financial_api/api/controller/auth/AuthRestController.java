package test.project.financial_api.api.controller.auth;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.project.financial_api.api.dto.auth.AuthRequest;
import test.project.financial_api.api.dto.auth.AuthResponse;
import test.project.financial_api.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthRestController implements AuthController {
  
  AuthService authService;
  
  
  @Override
  public ResponseEntity<AuthResponse> login(final AuthRequest authRequest) {
    return ResponseEntity.ok(authService.login(authRequest));
  }
}
