package com.pragma.userservice.infraestructure.out.jpa.adapter;

import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.domain.spi.IRolePersistencePort;
import com.pragma.userservice.infraestructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.userservice.infraestructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleMysqlAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    @Override
    public void save(RoleModel roleModel) {
        roleRepository.save(roleEntityMapper.mapToRoleEntity(roleModel));
    }

    @Override
    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }

    @Override
    public List<RoleModel> getAllRoles() {
        return roleEntityMapper.mapToRoleModelList(roleRepository.findAll());
    }

    @Override
    public RoleModel findById(Long id) {
        return roleEntityMapper.mapToRoleModel(roleRepository.findById(id).orElse(null));
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
