package test.project.financial_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import test.project.financial_api.api.dto.AppUserInfoResponse;
import test.project.financial_api.api.dto.user.AppUserCreateRequest;
import test.project.financial_api.api.dto.user.AppUserUpdateRequest;
import test.project.financial_api.persistence.entity.AppUserEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppUserMapper {
  
  AppUserInfoResponse fromEntityToDto(AppUserEntity entity);
  
  @Mapping(target = "password", ignore = true)
  AppUserEntity fromDtoToEntity(AppUserCreateRequest dto);
  
  @Mapping(target = "password", ignore = true)
  void updateEntityToDto(@MappingTarget AppUserEntity entity, AppUserUpdateRequest dto);
}
