package test.project.financial_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import test.project.financial_api.api.dto.PageResponse;
import test.project.financial_api.api.dto.transfer.TransferInfoResponse;
import test.project.financial_api.persistence.entity.TransferEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransferMapper {
  TransferInfoResponse fromEntityToDto(TransferEntity entity);
  
  default PageResponse<TransferInfoResponse> pageResponse(Page<TransferEntity> page) {
    return new PageResponse<>(page.getContent().stream().map(this::fromEntityToDto).toList(), page.getNumber(),
      page.getSize(), page.getTotalElements(), page.getTotalPages(), page.isLast());
  }
}
