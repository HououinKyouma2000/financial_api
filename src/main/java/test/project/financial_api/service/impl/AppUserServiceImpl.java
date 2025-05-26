package test.project.financial_api.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.project.financial_api.api.dto.user.AppUserCreateRequest;
import test.project.financial_api.api.dto.user.AppUserUpdateRequest;
import test.project.financial_api.expection.AppUserAlreadyExistException;
import test.project.financial_api.expection.AppUserNotFoundException;
import test.project.financial_api.mapper.AppUserMapper;
import test.project.financial_api.persistence.entity.AccountEntity;
import test.project.financial_api.persistence.entity.AppUserEntity;
import test.project.financial_api.persistence.repository.AppUserEntityRepository;
import test.project.financial_api.service.AccountService;
import test.project.financial_api.service.AppUserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppUserServiceImpl implements AppUserService {
  
  AppUserEntityRepository appUserEntityRepository;
  AppUserMapper appUserMapper;
  PasswordEncoder passwordEncoder;
  AccountService accountService;
  
  
  @Override
  @Transactional
  public AppUserEntity create(final AppUserCreateRequest request) {
    if (appUserEntityRepository.findByName(request.name()).isPresent()) {
      throw new AppUserAlreadyExistException();
    }
    
    final AppUserEntity appUser = appUserMapper.fromDtoToEntity(request);
    final AccountEntity accountEntity = accountService.create(request.balance());
    
    appUser.initialize(passwordEncoder.encode(request.password()), accountEntity);
    return appUserEntityRepository.save(appUser);
  }
  
  @Override
  @Transactional
  public AppUserEntity update(final AppUserUpdateRequest request) {
    if (appUserEntityRepository.findByName(request.name()).isPresent()) {
      throw new AppUserAlreadyExistException();
    }
    
    final AppUserEntity appUser = this.getUserFromSecurityContext();
    appUserMapper.updateEntityToDto(appUser, request);
    if (request.password() != null) {
      appUser.setPassword(passwordEncoder.encode(request.password()));
    }
    return appUserEntityRepository.save(appUser);
  }
  
  @Override
  @Transactional(readOnly = true)
  public AppUserEntity getUserFromSecurityContext() {
    return appUserEntityRepository.findById(UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName()))
      .orElseThrow(AppUserNotFoundException::new);
  }
  
}
