package com.pragma.userservice.application.mapper;

import com.pragma.userservice.application.dto.request.RoleRequest;
import com.pragma.userservice.application.dto.response.RoleResponse;
import com.pragma.userservice.domain.model.RoleModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-18T15:58:29-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class IRoleDtoMapperImpl implements IRoleDtoMapper {

    @Override
    public RoleModel mapToRoleModel(RoleRequest roleRequest) {
        if ( roleRequest == null ) {
            return null;
        }

        RoleModel roleModel = new RoleModel();

        roleModel.setName( roleRequest.getName() );
        roleModel.setDescription( roleRequest.getDescription() );

        return roleModel;
    }

    @Override
    public List<RoleResponse> mapToRolResponseDtoList(List<RoleModel> roleModelList) {
        if ( roleModelList == null ) {
            return null;
        }

        List<RoleResponse> list = new ArrayList<RoleResponse>( roleModelList.size() );
        for ( RoleModel roleModel : roleModelList ) {
            list.add( roleModelToRoleResponse( roleModel ) );
        }

        return list;
    }

    protected RoleResponse roleModelToRoleResponse(RoleModel roleModel) {
        if ( roleModel == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = roleModel.getId();
        name = roleModel.getName();
        description = roleModel.getDescription();

        RoleResponse roleResponse = new RoleResponse( id, name, description );

        return roleResponse;
    }
}
