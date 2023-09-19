package com.pragma.userservice.infraestructure.out.jpa.mapper;

import com.pragma.userservice.domain.model.UserModel;
import com.pragma.userservice.infraestructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {
    UserEntity mapToUserEntity(UserModel userModel);
    UserModel mapToUserModel(UserEntity userEntity);
}
