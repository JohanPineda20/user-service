package com.pragma.userservice.infraestructure.out.jpa.mapper;

import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.domain.model.UserModel;
import com.pragma.userservice.infraestructure.out.jpa.entity.RoleEntity;
import com.pragma.userservice.infraestructure.out.jpa.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-18T19:52:23-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class IUserEntityMapperImpl implements IUserEntityMapper {

    @Override
    public UserEntity mapToUserEntity(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userModel.getId() );
        userEntity.setName( userModel.getName() );
        userEntity.setLastname( userModel.getLastname() );
        userEntity.setDocumentNumber( userModel.getDocumentNumber() );
        userEntity.setCellphone( userModel.getCellphone() );
        userEntity.setBirthdate( userModel.getBirthdate() );
        userEntity.setEmail( userModel.getEmail() );
        userEntity.setPassword( userModel.getPassword() );
        userEntity.setRole( roleModelToRoleEntity( userModel.getRole() ) );

        return userEntity;
    }

    protected RoleEntity roleModelToRoleEntity(RoleModel roleModel) {
        if ( roleModel == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( roleModel.getId() );
        roleEntity.setName( roleModel.getName() );
        roleEntity.setDescription( roleModel.getDescription() );

        return roleEntity;
    }
}
