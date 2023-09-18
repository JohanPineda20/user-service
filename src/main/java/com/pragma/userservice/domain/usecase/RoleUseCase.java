package com.pragma.userservice.domain.usecase;

import com.pragma.userservice.domain.api.IRoleServicePort;
import com.pragma.userservice.domain.exception.DataAlreadyExistsException;
import com.pragma.userservice.domain.exception.DataNotFoundException;
import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.domain.spi.IRolePersistencePort;

import java.util.List;

public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public void save(RoleModel roleModel) {
        if(rolePersistencePort.existsByName(roleModel.getName())){
            throw new DataAlreadyExistsException("Role already exists");
        }
        rolePersistencePort.save(roleModel);
    }

    @Override
    public List<RoleModel> getAllRoles() {
        List<RoleModel> roleModelList = rolePersistencePort.getAllRoles();
        if(roleModelList.isEmpty()){
            throw new DataNotFoundException("There aren't roles");
        }
        return roleModelList;
    }

    @Override
    public void delete(Long id) {
        RoleModel roleModel = rolePersistencePort.findById(id);
        if(roleModel==null){throw new DataNotFoundException("Role not found");}
        rolePersistencePort.delete(id);
    }
}
