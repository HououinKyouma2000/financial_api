package test.project.financial_api.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import test.project.financial_api.api.dto.auth.AuthRequest;
import test.project.financial_api.api.dto.auth.AuthResponse;
import test.project.financial_api.expection.AppUserNotFoundException;
import test.project.financial_api.persistence.entity.AppUserEntity;
import test.project.financial_api.persistence.repository.AppUserEntityRepository;
import test.project.financial_api.service.AuthService;
import test.project.financial_api.service.JwtService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
  
  JwtService jwtService;
  AuthenticationManager authenticationManager;
  AppUserEntityRepository appUserEntityRepository;
  
  
  @Override
  public AuthResponse login(final AuthRequest request) {
    final AppUserEntity user = appUserEntityRepository.findByName(request.name()).orElseThrow(AppUserNotFoundException::new);
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
      user.getId().toString(),
      request.password()
    ));
    
    final String jwt = jwtService.generateToken(user);
    return new AuthResponse(jwt);
  }
}
