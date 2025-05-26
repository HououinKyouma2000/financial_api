package test.project.financial_api.service;

import test.project.financial_api.api.dto.AppUserInfoResponse;
import test.project.financial_api.api.dto.user.AppUserCreateRequest;
import test.project.financial_api.api.dto.user.AppUserUpdateRequest;
import test.project.financial_api.persistence.entity.AppUserEntity;

public interface AppUserService {
  AppUserInfoResponse create(AppUserCreateRequest request);
  
  AppUserInfoResponse update(AppUserUpdateRequest request);
  
  AppUserEntity getUserEntityFromSecurityContext();
  
  AppUserInfoResponse getUserFromSecurityContext();
}
