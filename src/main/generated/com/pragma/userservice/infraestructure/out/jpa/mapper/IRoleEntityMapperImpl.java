package com.pragma.userservice.infraestructure.out.jpa.mapper;

import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.infraestructure.out.jpa.entity.RoleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-18T18:53:55-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class IRoleEntityMapperImpl implements IRoleEntityMapper {

    @Override
    public RoleEntity mapToRoleEntity(RoleModel roleModel) {
        if ( roleModel == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( roleModel.getId() );
        roleEntity.setName( roleModel.getName() );
        roleEntity.setDescription( roleModel.getDescription() );

        return roleEntity;
    }

    @Override
    public RoleModel mapToRoleModel(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleModel roleModel = new RoleModel();

        roleModel.setId( roleEntity.getId() );
        roleModel.setName( roleEntity.getName() );
        roleModel.setDescription( roleEntity.getDescription() );

        return roleModel;
    }

    @Override
    public List<RoleModel> mapToRoleModelList(List<RoleEntity> roleEntityList) {
        if ( roleEntityList == null ) {
            return null;
        }

        List<RoleModel> list = new ArrayList<RoleModel>( roleEntityList.size() );
        for ( RoleEntity roleEntity : roleEntityList ) {
            list.add( mapToRoleModel( roleEntity ) );
        }

        return list;
    }
}
