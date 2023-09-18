package com.pragma.userservice.application.mapper;

import com.pragma.userservice.application.dto.request.UserRequest;
import com.pragma.userservice.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserDtoMapper {
    @Mapping(target="birthdate", source = "birthdate",
            dateFormat = "dd/MM/yyyy")
    UserModel mapToUserModel(UserRequest userRequest);
}
