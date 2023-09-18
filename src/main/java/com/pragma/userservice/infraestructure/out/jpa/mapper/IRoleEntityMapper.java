package com.pragma.userservice.infraestructure.out.jpa.mapper;

import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.infraestructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRoleEntityMapper {
    RoleEntity mapToRoleEntity(RoleModel roleModel);
    RoleModel mapToRoleModel(RoleEntity roleEntity);
    List<RoleModel> mapToRoleModelList(List<RoleEntity> roleEntityList);
}
