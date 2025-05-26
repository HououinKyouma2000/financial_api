package test.project.financial_api.api.controller.user;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.project.financial_api.api.dto.AppUserInfoResponse;
import test.project.financial_api.api.dto.user.AppUserCreateRequest;
import test.project.financial_api.api.dto.user.AppUserUpdateRequest;
import test.project.financial_api.service.AppUserService;

@RestController
@RequestMapping("/api/v1/app-user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppUserRestController implements AppUserController {
  
  AppUserService appUserService;
  
  @Override
  public ResponseEntity<AppUserInfoResponse> create(final AppUserCreateRequest appUserCreateRequest) {
    return ResponseEntity.ok(
      appUserService.create(appUserCreateRequest)
    );
  }
  
  @Override
  public ResponseEntity<AppUserInfoResponse> get() {
    return ResponseEntity.ok(
      appUserService.getUserFromSecurityContext()
    );
  }
  
  @Override
  public ResponseEntity<AppUserInfoResponse> update(final AppUserUpdateRequest appUserUpdateRequest) {
    return ResponseEntity.ok(
      appUserService.update(appUserUpdateRequest)
    );
  }
}
