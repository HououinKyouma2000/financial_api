package test.project.financial_api.service;

import test.project.financial_api.api.dto.user.AppUserCreateRequest;
import test.project.financial_api.api.dto.user.AppUserUpdateRequest;
import test.project.financial_api.persistence.entity.AppUserEntity;

public interface AppUserService {
  AppUserEntity create(AppUserCreateRequest request);
  
  AppUserEntity update(AppUserUpdateRequest request);
  
  AppUserEntity getUserFromSecurityContext();
}
