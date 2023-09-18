package com.pragma.userservice.application.mapper;

import com.pragma.userservice.application.dto.request.RoleRequest;
import com.pragma.userservice.application.dto.response.RoleResponse;
import com.pragma.userservice.domain.model.RoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRoleDtoMapper {
RoleModel mapToRoleModel(RoleRequest roleRequest);

List<RoleResponse> mapToRolResponseDtoList(List<RoleModel> roleModelList);

}
